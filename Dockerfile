FROM openjdk/8
RUN apt-get update
RUN apt-get install -y maven
COPY ./src /src
# RUN javac ./src/com/tutorial/main/Game.java

WORKDIR /src
RUN mvn package
RUN cd /src
RUN javac com/tutorial/main/Game.java

CMD [ "java", "com.kagaries.main.Game" ]