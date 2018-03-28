# Volumes

## Creation

    kubectl create -f fortune-pod.yml

Two containers are running sharing an `emptyDir` volume. The first one use "fortune" to write to a web page and the other 
one is a web server serving such a page.

If one container is killed, it's restarted and the file content is still there. The volume is "emptied" on pod restarting.

Access through port forwarding. Local traffic is forwarded to the specific pod and related port.

    kubectl port-forward fortune 8080:8080
    
using browser with address `http://localhost:8080`.