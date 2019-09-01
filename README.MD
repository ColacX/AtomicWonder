# README

# Compile
javac -d bin src/*.java -cp ./lib/*.jar

# Run
sudo java -cp bin:lib/* Designer
sudo java -cp bin:lib/* Program

# Compile && Run
javac -d bin src/*.java -cp ./lib/*.jar && sudo java -cp bin:lib/* Program