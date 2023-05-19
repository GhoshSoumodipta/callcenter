#!/bin/sh
curl -v --header "Content-Type: application/json" --data @data.json http://localhost:8080/api/v1/authentication
