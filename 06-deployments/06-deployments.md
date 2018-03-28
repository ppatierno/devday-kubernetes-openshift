# Deployments

## Creation

    kubectl create -f vertx-http-app-dep.yml
    kubectl create -f vertx-http-app-svc.yml

Start to access to the application sending more requests

    ../curl.sh http://[IP address]:[Port] 1000

Execute the update of the application image. It will use a "Rolling Update" strategy by default, so starting one pod at 
time while shutting down an old one (so not shutting down all old Pods before starting the new ones). 

    kubectl set image deployment vertx-http-app vertx-http-app=ppatierno/vertx-http-app:v2

Checking the ReplicaSets and noticing that there are two of them; the old and the new one. The new one is now handling 
the replicas with the new application while the old one is still there but with no Pods to handle; it's used for doing a
roll back.

> for creating a new Deployment, the `run` command can be used as follow `kubectl run [name] --image=[image-name]