echo "pure"
rm -fr client.*
rm -fr tomcat.*
rm -fr ca.*

echo "genrsa & ca start"
openssl genrsa -out ca.key 2048
openssl req -new -x509 -days 3650 -key ca.key -out ca.crt -subj "/C=CN/ST=GD/L=SZ/O=test/OU=test/CN=*.ca/emailAddress=test@test.com"
keytool -importcert -alias test -file ca.crt -keystore ca.truststore
# keytool -list -keystore ca.truststore
#openssl x509 -in ca.crt -out ca.pem -outform PEM
echo "genrsa & ca end"

echo "gen tomcat certificate"
#生成密钥对
openssl genrsa -out tomcat.key 2048
#生成csr
openssl req -new -key tomcat.key -out tomcat.csr -subj "/C=CN/ST=GD/L=SZ/O=test/OU=test/CN=*.demo/emailAddress=test@test.com"
#生成证书
openssl x509 -req -sha256 -in tomcat.csr -CA ca.crt -CAkey ca.key -CAcreateserial -days 3650 -out tomcat.crt
#导出
openssl pkcs12 -export -in tomcat.crt -inkey tomcat.key -out tomcat.p12
#导入
keytool -importkeystore -srckeystore tomcat.p12 -srcstoretype pkcs12 -destkeystore tomcat.keystore -deststoretype pkcs12
keytool -importcert -alias tomcat -file tomcat.crt -keystore ca.truststore


echo "gen client certificate"
#生成密钥对
openssl genrsa -out client.key 2048
#生成csr
openssl req -new -key client.key -out client.csr -subj "/C=CN/ST=GD/L=SZ/O=test/OU=test/CN=*.demo/emailAddress=test@test.com"
#生成证书
openssl x509 -req -sha256 -in client.csr -CA ca.crt -CAkey ca.key -CAcreateserial -days 3650 -out client.crt
#导出
openssl pkcs12 -export -in client.crt -inkey client.key -out client.p12
#导入
keytool -importkeystore -srckeystore client.p12 -srcstoretype pkcs12 -destkeystore client.keystore -deststoretype pkcs12
keytool -importcert -alias client -file client.crt -keystore ca.truststore
echo "finished"

echo "keytool -list -keystore ca.truststore "

keytool -list -keystore ca.truststore

echo "cp certificate"
cp client.* ../nt-gateway/src/main/resources/
cp ca.* ../nt-gateway/src/main/resources/
cp tomcat.* ../nt-ms/src/main/resources/
cp ca.* ../nt-ms/src/main/resources/

echo "build"
mvn compile -f ../nt-gateway/pom.xml
mvn compile -f ../nt-ms/pom.xml

#test
#curl -k --cacert ./ca.crt --key ./client.key --cert ./client.crt https://127.0.0.1:9001/nt-gw/ms-data
