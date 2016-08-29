#!/usr/bin/env bash

rm -r -f build/burp
rm -f bin/burpextender.jar
javac -cp lib/burp-api.jar -d build src/burp/*.java
jar cf bin/burpextender.jar res -C build burp
