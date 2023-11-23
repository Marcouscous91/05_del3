@echo off
mvn clean install
java -cp target/classes cdio3.Monopoly
pause
