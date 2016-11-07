#!/bin/bash
IN=$2
if [ -z "$IN" ]
	then
		IN=$(basename `ls -t ~/Downloads | head -1` .in)
fi
cp -rf ~/Downloads/$IN.in io/16/r1C/
mvn -q exec:java -Dexec.classpathScope=runtime -Dexec.mainClass="zi.jam.y16.r1C.$1" <io/16/r1C/$IN.in | tee io/16/r1C/$IN.out
cp -rf io/16/r1C/$IN.out /Users/balaudt/Temp/r1C/
cp -rf src/main/java/zi/jam/y16/r1C/$1.scala /Users/balaudt/Temp/r1C/
