#!/bin/bash

export LC_CTYPE=en_US.UTF-8

WORK_DIR=$(pwd)

BINDIR="../bin"

mkdir $BINDIR

# 使用内存
MEM=3G

XMN=1G

# 入口类
MainClass=app.DubboApplication

CLASSPATH=.:conf
for jar in `find . -name '*.jar'` 
do
    CLASSPATH=$CLASSPATH:$jar
done

# 不用-cp或者-classpath命令行参数，否则易导致在查看ps -ef命令结果时，依赖JAR包信息占据绝大部分篇幅
export CLASSPATH

java -server -Xmx$MEM -Xms$MEM -Xmn$XMN -Xss1024k -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+UseCMSCompactAtFullCollection $MainClass
