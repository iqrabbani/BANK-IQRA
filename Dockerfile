# Base image for Java
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy built jar into container
COPY target/app.jar app.jar

# Copy wait-for-it script into container
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Wait for MySQL to be ready, then run the app
CMD ["/wait-for-it.sh", "db:3306", "--", "java", "-jar", "/app/app.jar"]

