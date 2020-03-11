FROM openjdk:11.0.6-slim
ADD target/payments.jar /
CMD java -jar \
    -Dspring.profiles.active=$ACTIVE_PROFILE \
    -Dspring.cloud.config.uri=$CONFIGURATION_SERVER_URI \
    payments.jar
