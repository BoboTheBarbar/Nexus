FROM openjdk:11-jdk-slim-buster
RUN apt-get update && apt-get install -y curl unzip && \
    curl -o /tmp/maven.zip https://mirrors.ae-online.de/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip
RUN \
    cd /tmp && \
    unzip /tmp/maven.zip && \
    ln -s apache-maven-* maven
ENV PATH=$PATH:/tmp/maven/bin
RUN echo $PATH
ADD pom.xml /app/
RUN mvn compile ; exit 0
ADD . /app/
WORKDIR /app/
RUN mvn package
CMD mvn exec:java


# docker build -t wholesale:1 .
# docker run -it wholesale:1