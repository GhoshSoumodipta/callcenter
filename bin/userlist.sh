#!/bin/sh
curl -v -H "Authorization: Bearer ${1}" http://localhost:8080/users
