#FROM openjdk:21-jdk
## ADD ./target/dashboard-jar-with-dependencies.jar dashboard.jar
## EXPOSE 8080
## ENV SPRING_PROFILES_ACTIVE=docker
## CMD [ "java", "-jar", "dashboard.jar"]
#
#ARG DEPENDENCY=target/dependency
#COPY target/lib /app/lib
#COPY target/classes/application.properties /app/config
#COPY target/UIdashboard-0.0.1-SNAPSHOT.jar /app/UIdashboard-0.0.1-SNAPSHOT.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java","-cp","app/*:app/lib/*:/app/config/*","com.ceadar.uidashboard.UIdashboardApplication"]

# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set environment variables if needed (optional)
# ENV SPRING_PROFILES_ACTIVE=prod

# Set working directory
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/UIdashboard-0.0.1-SNAPSHOT.jar /app/UIdashboard-0.0.1-SNAPSHOT.jar

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/UIdashboard-0.0.1-SNAPSHOT.jar"]