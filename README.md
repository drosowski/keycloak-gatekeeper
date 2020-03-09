# Keycloak and Gatekeeper and Spring Boot Demo

:information_source: The aim of this demo project is to give the reader a blueprint for using Keycloak-based authentication and authorisation in a Spring Boot app.

There are three different ways to protect a Spring Boot app using Keycloak that we are going to investigate.

1. Use the Spring Boot adapter
2. Use the Spring Security adapter
3. Use Gatekeeper

## Prerequisites

You need a running kubernetes cluster.
It should work on both `minikube` or `k3s`.

1. `kubectl apply -f keycloak`
2. `sudo echo "127.0.0.1       keycloak.keycloak.svc" >> /etc/hosts`
3. `kubectl port-forward <KEYCLOAK_POD_NAME> 8180:8180 -n keycloak`

## Test

If you're using IntelliJ (as you should ;-) you will find some *.http files in `src/test/resources` that you can use to test whether authentication works as expected.

1. Run `greet.http` and you should get a polite but resolute `401 (Unauthorized)`.
2. Run `authenticate.http` and copy the contents of `access_token`
3. Put the token after `Bearer` in the `greet.http` and run it again.
3. :tada:

`setgreeting.http` requires the role 'edit', so we have to login with the `gsadmin` user.
Otherwise you will get a passive aggressive but expressive `403 (Forbidden)`.

This way you can test the different integrations of Keycloak. Have fun and stay safe!

## Gatekeeper

The configuration for gatekeeper is in the `master` branch.
As gatekeeper runs as a sidecar container along the greetingservice, you have to run the service in the kubernetes cluster.

In the `rest` directory:

1. `mvn compile jib:dockerBuild`
2. `kubectl apply -f k8s`

The service is then available at `http://localhost:30080` (see `greet.http` and `setgreeting.http`).

## Spring Boot Adapter

https://www.keycloak.org/docs/latest/securing_apps/index.html#_spring_boot_adapter

The configuration for the Spring Boot adapter can be found in the branch `springboot-adapter`.
Just start the app with the main class (`RestServiceApplication`) from your IDE.

## Spring Security Adapter

https://www.keycloak.org/docs/latest/securing_apps/index.html#_spring_security_adapter

The configuration for the Spring Boot adapter can be found in the branch `springsecurity-adapter`. 
Just start the app with the main class (`RestServiceApplication`) from your IDE.