FROM openjdk:17
#VOLUME /tmp
WORKDIR /app
EXPOSE 8080
COPY target/medical-appointment-1.jar medical-appointment-1.jar
ENTRYPOINT ["java","-jar","medical-appointment-1.jar"]
