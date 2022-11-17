FROM openjdk
WORKDIR usr/lib
ADD ./target/customerPc1-0.0.1-SNAPSHOT.jar  /usr/lib/customerPc1-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","customerPc1-0.0.1-SNAPSHOT.jar"]