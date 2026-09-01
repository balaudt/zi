IN=$2
if [ -z "$IN" ]
	then
		IN=$(basename `ls -t ~/Downloads | head -1` .in)
fi
cp -rf ~/Downloads/$IN.in io/17/fb/qual
mvn -q exec:java -Dexec.classpathScope=runtime -Dexec.mainClass="zi.fb.cup17.qual.$1" <io/17/fb/qual/$IN.in | tee io/17/fb/qual/$IN.out
cp -rf io/17/fb/qual/$IN.out /Users/balaudt/Temp/fb/cup17/qual
cp -rf src/main/java/zi/fb/cup17/qual/$1.scala /Users/balaudt/Temp/fb/cup17/qual
