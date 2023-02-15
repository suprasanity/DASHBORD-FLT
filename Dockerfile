FROM FROM eclipse-temurin:17
RUN apt-get update && apt-get install -y curl
RUN curl -sL https://deb.nodesource.com/setup_18.x | bash -
RUN apt-get update && apt-get install -y nodejs
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 80
COPY docker-compose.yml .
CMD ["java", "-jar", "target/website-0.0.1-SNAPSHOT.jar"]
