FROM openjdk:8

LABEL  "Nicola Mincuzzi"="n.mincuzzi@outlook.com"

ENV MAVEN_VERSION 3.6.0

RUN mkdir -p /usr/share/maven && curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

VOLUME /root/.m2

COPY . /usr/src/

WORKDIR /usr/src/

EXPOSE 9090

#RUN mvn package -Dmaven.test.skip=true

#CMD [ "java", "-cp", "target/MazeRetroRoutePuzzle-jar-with-dependencies.jar", "it.subito.routepuzzle.main.MazeRetroRoutePuzzle" ]

CMD [ "/bin/bash"]