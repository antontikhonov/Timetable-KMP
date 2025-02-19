# Use a JDK base image with Gradle 8.9
FROM gradle:8.9-jdk17 AS build

WORKDIR /app
COPY . .

RUN ./gradlew wasmJsBrowserDistribution

FROM nginx:stable-alpine

COPY --from=build /app/composeApp/build/dist/wasmJs/productionExecutable /var/www/timetable-site/rasp
#COPY --from=build /app/composeApp/build/dist/wasmJs/productionExecutable /usr/share/nginx/html

#EXPOSE 3000
