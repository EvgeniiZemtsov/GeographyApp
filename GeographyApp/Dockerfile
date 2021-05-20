FROM adoptopenjdk/openjdk11:ubi
RUN mkdir /opt/geo_app
WORKDIR /opt/geo_app
COPY GeographyApp-1.0-SNAPSHOT.jar /opt/geo_app
CMD ["java", "-jar", "GeographyApp-1.0-SNAPSHOT.jar"]