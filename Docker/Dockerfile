FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY PasswordAnalyzer.java .

RUN javac PasswordAnalyzer.java

ENTRYPOINT ["java", "PasswordAnalyzer"]