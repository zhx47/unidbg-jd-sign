#!/bin/sh
set -e

if [ ! -d "/app/data" ]; then
  mkdir /app/data
fi

if [ ! -f "/app/data/jd.apk" ]; then
  cp /app/init/jd.apk /app/data/
fi

if [ ! -f "/app/data/libjdbitmapkit.so" ]; then
  cp /app/init/libjdbitmapkit.so /app/data/
fi

# 启动应用程序
exec java -jar /app/unidbg-jd-sign-0.0.1-SNAPSHOT.jar
