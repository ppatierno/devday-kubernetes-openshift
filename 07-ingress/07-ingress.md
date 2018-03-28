# Ingress

> In order to follow this ingress example, the vertx-http-app and a related service needs to be deployed.
For that, the ReplicaSets and Services examples can be used.

Running the following command

    kubectl get pods -n kube-system

It's possible to see the deployed "ingress controller" which is in charge to handle the ingress.

## Creation

    kubectl create -f ingress.yml

It's possible to check the IP address assigned to the ingress

    kubectl get ingresses
    
which in case of using Minikube, it's the Minikube VM IP address (as with `minikube ip` command).

```
NAME             HOSTS                ADDRESS          PORTS     AGE
vertx-http-app   vertx.http-app.com   192.168.42.232   80        57s
``` 

In order to try the ingress locally it's needed to add the DNS resolution to the `etc/hosts`; adding for example the 
following line.

    192.168.42.232  vertx.http-app.com
    
After that, the ingress can be used with

    curl http://vertx.http-app.com:80

> Port `80` is used because the ingress is configured as using HTTP (so default port is 80).

    