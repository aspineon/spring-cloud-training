FROM openjdk:11.0.4-stretch
ADD target/configuration.jar /
CMD java -jar /
    -Dspring.profiles.active=$ACTIVE_PROFILE \
    -Dspring.cloud.stream.kafka.binder.brokers=$KAFKA_SERVER \
    configuration.jar