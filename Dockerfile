# Start with a base image containing Java runtime
FROM java:8

# Add Author info
LABEL maintainer="1cktmdgh2@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 80 available to the world outside this container
EXPOSE 80

# The application's jar file
ARG WAR_FILE=build/libs/memorylog_Server-0.0.1-SNAPSHOT.war

# Add the application's jar to the container
ADD ${WAR_FILE} app

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "app"]