kubectl delete -f service/pup/pup-backend-deployment.yaml
sudo k3s crictl rmi --prune
kubectl apply -f service/pup/pup-backend-deployment.yaml
kubectl apply -f service/pup/pup-backend-service.yaml