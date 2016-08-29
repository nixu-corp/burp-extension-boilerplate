#!/usr/bin/env bash

echo Removing old files
rm -r -f build\burp
rm -f lib\burp-api.jar
echo Compiling Burp API files
javac -d build burp-api\burp\*.java
echo Packaging into lib\burp-api.jar
jar cf lib\burp-api.jar -C build burp
echo Burp API updated