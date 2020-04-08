FROM openjdk:11
COPY target/keypair-management-0.0.1-SNAPSHOT.jar /cmp_stack/test/
WORKDIR /cmp_stack/test/
CMD java -jar keypair-management-0.0.1-SNAPSHOT.jar
EXPOSE 8064
