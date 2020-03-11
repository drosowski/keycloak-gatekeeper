#!/bin/sh

RESULT=`curl -k --data "username=gsuser&password=gspass&grant_type=password&scope=openid&client_id=greetingservice-client&client_secret=57803581-1d67-4851-9646-59b73018b6b4" http://keycloak.keycloak.svc:8180/auth/realms/greetingservice/protocol/openid-connect/token`
JWT=`echo $RESULT | sed 's/[^.]*.\([^.]*\).*/\1/'`
echo $JWT | base64 -d | python -m json.tool
