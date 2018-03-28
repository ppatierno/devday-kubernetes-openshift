# ReplicaSets

## Creation

    kubectl create -f vertx-http-app-rs.yml

> for the following examples is interesting to watch the pods in this way `watch -n 1 kubectl get pods --show-labels`
    
Deleting one pod, a new one will be restarted.

## ReplicaSets supervision

If you change the label for one pod so that it doesn't match the ReplicaSet selection anymore, the pod is not controlled 
anymore and it becomes a standalone pod; the controller creates a new pod for matching the requested replicas.

    kubectl edit pod [pod-name]

If you change the label selector on the ReplicaSet and the label for Pods template, it will create new pods and the 
previous ones will become standalone pod, not under the controller anymore.

    kubectl edit replicasets [rs-name]
    
If you change the label for one of the not handled Pods in order to match the selector in the ReplicaSet, then a Pod will 
be killed in order to have the requested replicas number.

Deleting the ReplicaSet will delete all the related pods

    kubectl delete replicaset vertx-http-app
    
## Scaling up/down

    kubectl scale replicaset vertx-http-app --replicas=5
    
    kubectl scale replicaset vertx-http-app --replicas=2
    
> it's also possible to scale up/down editing the current ReplicaSet YAML definition with `kubectl edit replicaset vertx-http-app`

In order to have rolling updates feature, it's better using Deployment instead of ReplicaSet



