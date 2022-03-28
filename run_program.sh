#!/bin/bash

javac -d bin src/classes/*.java
java -cp bin classes.Registration
