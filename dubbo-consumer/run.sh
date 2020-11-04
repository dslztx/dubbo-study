#!/bin/bash

CLASSPATH=.

for jar in target/*.jar
do
    CLASSPATH=$CLASSPATH:$jar
done

for jar in target/dependency/*.jar
do
    CLASSPATH=$CLASSPATH:$jar
done

export CLASSPATH

java -Dcsp.sentinel.dashboard.server=10.110.20.89:8900 service.DubboClient
