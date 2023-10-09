# CubeMan
Updated version of a game I made 2 years ago

I can't test the docker right now because it really hates ARM64 with Java

You should try to run it, but no guarantees

To run the game: download java 8+ and download the game jar from one of the releases

To run the game with docker:

cd into_the_directory

docker build -t [name_of_image_you_want] ./

docker run -e DISPLAY=:0.0 -it [name_of_image]
