IF EXIST build\burp RMDIR /S /Q build\burp
IF EXIST bin\burpextender.jar DEL bin\burpextender.jar
javac -cp lib\burp-api.jar -d build src\burp\*.java
jar cf bin/burpextender.jar res -C build burp
