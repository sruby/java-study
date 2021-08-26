echo "docker build"
#第一个入参作为tag
docker build -t test-server:$1 .
echo "docker push" docker push test-server:$1
cp test-demo-deployment-template.yaml test-demo-deployment.yaml
#替换tag
sed -i "s/\${image-tag}/$1/g" test-demo-deployment.yaml 
echo "kubectl apply deployment"
kubectl apply -f test-demo-deployment.yaml -n test-demo 
echo "get pods"
kubectl get po -n test-demo