# ConfigMaps

ConfigMap creation

    kubectl create -f vertx-http-app-cm.yml

Deploying application

    kubectl create -f vertx-http-app-pod-cm.yml

Access through port forwarding. Local traffic is forwarded to the specific pod and related port.

    kubectl port-forward vertx-http-app 8080:8080
    
using browser with address `http://localhost:8080`.