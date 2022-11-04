FROM openjdk:11
VOLUME /tmp
EXPOSE 8087
ADD ./target/ms-person-type-0.0.1-SNAPSHOT.jar ms-person-type.jar
ENTRYPOINT ["java","-jar","/ms-person-type.jar"]