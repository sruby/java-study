#load image to minikube from docker
minikube image load kubernetes/spring-cloud-kubernetes-example-reload:0.0.4
# edit deployment for add serviceaccount: serviceAccountName: config-reader
kubectl edit deployment spring-cloud-reload

kubectl create configmap reload-example application.properties

#create configmap
kubectl create configmap reload-example --from-file application.properties --from-file ./src/main/resources/application-dev.properties

#replace configmap from one file
kubectl create configmap reload-example --from-file ./application.properties -o yaml --dry-run | kubectl replace -f -

#replace configmap from multi file(kubernetes)
kubectl create configmap reload-example --from-file ./application.properties --from-file ./application-kubernetes.properties -o yaml --dry-run | kubectl replace -f -
#replace configmap from multi file(dev)
kubectl create configmap reload-example --from-file ./application.properties --from-file ./src/main/resources/application-dev.properties -o yaml --dry-run | kubectl replace -f -


