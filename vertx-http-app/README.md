# Compiling applications

In order to compile the different Vert.x HTTP applications, the following Maven command can be used :

    mvn clean package

# Building Docker images

Different Docker images are needed for the examples and they can be built with following commands.

The main Vert.x HTTP application image.

    docker build -t ppatierno/vertx-http-app:latest -f ./src/docker-images/Dockerfile .

This image needs also be tagged as "v1" for using it during the rolling update example.

    docker tag ppatierno/vertx-http-app:latest ppatierno/vertx-http-app:v1
    
The Vert.x HTTP application with failure simulation.

    docker build -t ppatierno/vertx-http-app-failing:latest -f ./src/docker-images/Dockerfile-failing .

The Vert.x HTTP application "v2" for rolling update example.

    docker build -t ppatierno/vertx-http-app:v2 -f ./src/docker-images/Dockerfile-v2 .

Finally the "fortune" application and the related webserver.

    docker build -t ppatierno/fortune-web-server:latest f ./src/docker-images/Dockerfile-fortune-webserver .
    docker build -t ppatierno/fortune:latest -f ../fortune/Dockerfile .

# Using Minishift

If you are going to use Minishift for the examples and you have already built the images on your local Docker instance, 
you can push your locally built images to the Minishift virtual machine (instead of rebuilding them) with following command :

    docker save <image> | minishift ssh docker load