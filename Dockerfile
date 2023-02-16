FROM eclipse-temurin:17
RUN apt-get update && apt-get install -y curl
RUN curl -sL https://deb.nodesource.com/setup_18.x | bash -
RUN apt-get update && apt-get install -y nodejs
WORKDIR /app
COPY /src/main/java/front/frontendDashboard/test/package.json ./
RUN npm install
COPY . .
EXPOSE 80
COPY docker-compose.yml .
CMD ["java", "-jar", "target/website-0.0.1-SNAPSHOT.jar","NTgzOTk2MjU0ODYyNzA0NjUw.GYOTPC.voUKyxRWczTd1-_473gw3hAQJKaBi3s43HIOec"]
