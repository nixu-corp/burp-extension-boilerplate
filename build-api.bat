@echo off
     echo Removing old files
     IF EXIST build\burp RMDIR /S /Q build\burp
     IF EXIST lib\burp-api.jar DEL lib\burp-api.jar
     echo Compiling Burp API files
     javac -d build burp-api\burp\*.java
     echo Packaging into lib\burp-api.jar
     jar cf lib\burp-api.jar -C build burp
     echo Burp API updated
     pause