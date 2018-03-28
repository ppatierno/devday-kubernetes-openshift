# Pods

## Creation

    kubectl create -f vertx-http-app-pod.yml
    
Pod is not accessible outside the cluster but we can run a command on the Pod itself for sending a request to the application 
which in this case is reachable through localhost.

    kubectl exec -it vertx-http-app -- curl http://localhost:8080

Access through port forwarding. Local traffic is forwarded to the specific pod and related port.

    kubectl port-forward vertx-http-app 8080:8080
    
using browser with address `http://localhost:8080`.

## Deletion

    kubectl delete pods vertx-http-app

Pod is deleted and not rescheduled.

## Node selection

Un-comment lines related to the `nodeSelector` which specifies that the pod needs a node with SSD disk to be deployed.

    kubectl create -f vertx-http-app-pod.yml

After pod creation, it's in the `Pending` state because if a node exists with such a support, it's not defined through 
proper labels.

Getting nodes.

    kubectl get nodes --show-labels

Labeling the node with SSD support with `disktype=ssd`.

    kubectl label nodes localhost disktype=ssd

Now the pod is scheduled on the node and is running.

> remember to delete label using `kubectl label nodes localhost disktype-`. Of course the pod already scheduled is still running.

## Liveness and readiness probes

    kubectl create -f vertx-http-app-pod-liveness.yml

The readiness starts to execute HTTP GET requests after 15 (`initialDelaySeconds`) so it means that even if the container is 
running and the app is working fine, the Pod results as "not ready" before 15 seconds. 
    
The liveness probe starts to execute HTTP GET requests after 15 (`initialDelaySeconds`) seconds and then every 
10 seconds (default `periodSeconds`). The application returns HTTP 501 error after the 3 requests but the pod isn't restart 
after the first failure but after 3 consecutive failures (default `failureThreshold`). 