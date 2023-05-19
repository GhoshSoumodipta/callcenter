#!/bin/sh
./usercreate.sh objects customer.json ${1}
./usercreate.sh objects company.json ${1}
./usercreate.sh objects company-employee.json ${1}
./usercreate.sh objects company-employee2.json ${1}
./usercreate.sh objects company-leader.json ${1}
./usercreate.sh objects company-leader2.json ${1}
./usercreate.sh objects switching-call-center.json ${1} 
./usercreate.sh objects switching-call-center-employee.json ${1}
./usercreate.sh objects switching-call-center-employee2.json  ${1}
./usercreate.sh objects switching-call-center-leader.json  ${1}
./usercreate.sh objects switching-call-center-leader2.json  ${1}
./usercreate.sh objects interpreter.json  ${1}
