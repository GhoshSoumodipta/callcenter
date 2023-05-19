#!/bin/sh
# install liquibase zip from https://www.liquibase.com/download
# extract
# add liquibase root folder to path
# rename internal/lib/h2.jar to something like h2.jar.bak
# copy h2-<version>.jar from your local maven repo to liquibase install folder lib/
# restart cmd


liquibase --defaultsFile=src/main/resources/liquibase/liquibase.properties update
