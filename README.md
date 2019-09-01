# AtomicWonder
Atomic V Wand Led Program

# Compile
javac -d bin src/*.java -cp ./lib/*.jar

# Run
sudo java -cp bin:lib/* Designer
sudo java -cp bin:lib/* Program

# Compile && Run
javac -d bin src/*.java -cp ./lib/*.jar && sudo java -cp bin:lib/* Program

# Wishes
Graphical UI should not exist. Terminal support is all that is needed for programmers.
    Grapical UI are so much worse than other 3rd party tools for image editing.
    Instead they should send image-data to the device.
    Only noobs try to make their own GUI.
Device communication should provide a program slot id so it easy to transfer to a slot without having to manually choose the slot on the device
