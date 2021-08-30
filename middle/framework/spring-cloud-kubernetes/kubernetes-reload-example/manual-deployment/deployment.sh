#mvn clean package
### set docker env
eval $(minikube docker-env)

### build the repository
#mvn clean  install

### build the docker images on minikube
docker build -t reload-example .

kubectl delete configmap reload-example
kubectl delete -f deployment.yaml

kubectl create -f config-map.yml
kubectl create -f deployment.yaml

# Check that the pods are running
kubectl get pods
