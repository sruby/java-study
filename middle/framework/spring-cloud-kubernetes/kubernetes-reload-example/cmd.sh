kubectl create configmap reload-example application.properties

#create configmap
kubectl create configmap reload-example --from-file application.properties --from-file ./src/main/resources/application-dev.properties

#replace configmap
kubectl create configmap reload-example --from-file ./application.properties -o yaml --dry-run | kubectl replace -f -

#replace configmap
kubectl create configmap reload-example --from-file ./application.properties --from-file ./src/main/resources/application-dev.properties -o yaml --dry-run | kubectl replace -f -

