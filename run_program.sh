#!/bin/bash
if [ ! -d "bin" ]; then
	mkdir bin
	chmod 777 bin
fi
javac -d bin src/classes/*.java
java -cp bin classes.Registration
