# Pods

Creation

    kubectl create -f vertx-http-app-pod.yml

Access through port forwarding. Local traffic is forwarded to the specific pod and related port.

    kubectl port-forward vertx-http-app 8080:8080
    
using browser with address `http://localhost:8080`.

Node selection

Un-comment lines related to the `nodeSelector` which specifies that the pod needs a node with SSD disk to be deployed.

    kubectl create -f vertx-http-app-pod.yml

After pod creation, it's in the `Pending` state because if a node exists with such a support, it's not defined through 
proper labels.

Getting nodes.

    kubectl get nodes

Labeling the node with SSD support with `disktype=ssd`.

    kubectl label nodes localhost disktype=ssd

Now the pod is scheduled on the node and is running.

> remember to delete label using `kubectl label nodes localhost disktype-`. Of course the pod already scheduled is still running.

Deletion

    kubectl delete pods vertx-http-app

Pod is deleted and not rescheduled.

Liveness probe, pod creation.

    kubectl create -f vertx-http-app-pod-liveness.yml
    
The liveness probe starts to execute HTTP GET requests after 15 (`initialDelaySeconds`) seconds and then every 
10 seconds (default `periodSeconds`). The application returns HTTP 501 error after the 3 requests but the pod isn't restart 
after the first failure but after 3 consecutive failures (default `failureThreshold`). 