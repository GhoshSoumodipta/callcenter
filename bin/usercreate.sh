#!/bin/sh
curl -v -H "Authorization: Bearer ${3}" -H "Content-Type: application/json" --data @${2} http://localhost:8080/api/v1/${1}
