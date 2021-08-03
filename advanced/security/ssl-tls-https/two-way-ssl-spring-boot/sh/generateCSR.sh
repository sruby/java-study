openssl req -new -key tomcat.key -out tomcat.csr -subj "/C=CN/ST=GD/L=SZ/O=test/OU=test/CN=*.demo/emailAddress=test@test.com"
cat > v3.ext <<-EOF
authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
extendedKeyUsage = serverAuth
subjectAltName = @alt_names

[alt_names]
DNS.1=*.demo
DNS.2=*.multi.demo
IP.1=127.0.0.1
EOF