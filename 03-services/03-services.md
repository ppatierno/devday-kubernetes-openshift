# Services

> For this example it's necessary to deploy the ReplicaSets from "02-replicasets" example

## Creation
    
    kubectl create -f vertx-http-app-svc.yml

If the "NodePort" type is not active, the service is accessible only inside the cluster using its own IP address.

Enabling "NodePort", the service is accessible from outside the cluster and using a node IP address.

> Using minishift/minikube, its related IP address and node-port from service can be used

Sending more requests to the app, the traffic is load balanced between the different backed pods.

The `curl` script can be used for this purpose.

    curl [IP]:[PORT] [COUNT]