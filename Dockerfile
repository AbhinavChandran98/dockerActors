#FROM ubuntu:latest
#RUN apt update
#RUN apt upgrade
#RUN apt install curl -y
#RUN apt install iputils-ping -y
#RUN apt-get install net-tools
#RUN apt install -y openjdk-18-jdk
#RUN apt install -y openjdk-18-jre

FROM openjdk:18
LABEL maintainer="Nestdigital.net"
ADD target/patient-0.0.1-SNAPSHOT.jar patient
ENTRYPOINT ["java","-jar","patient"]