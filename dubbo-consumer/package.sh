#!/bin/bash 

mvn clean

mvn package -Dmaven.test.skip=true

mvn dependency:copy-dependencies

if [ ! -d "package" ];then
  mkdir package
else
  /bin/rm -rf package/* 
fi

mkdir package/lib
cp -r target/*.jar package/lib/
cp -r target/dependency/*.jar package/lib/

cp run.sh package/
