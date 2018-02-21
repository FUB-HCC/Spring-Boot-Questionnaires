# Spring-Boot Questionnaires

State: pre-alpha

## Introduction

This project aims to provide an out-of-the box integration of questionnaires/surveys based on
 - Spring Boot
 - Spring Data
 - Spring Web MVC with Handlebars.java

## Running the Demo Application
The demo module provides a small movie app, showcasing the usage and features of Spring-Boot Questionnaires.
In order to run the demo application, please proceed as follows:

#### 1. Clone the Repository

    git clone https://github.com/FUB-HCC/Spring-Boot-Questionnaires.git
    cd Spring-Boot-Questionnaires/
    
#### 2. Build the Project, using gradle

    ./gradlew build
    
#### 3. Run the demo-jar
Before running the demo, make sure that the local port 8080 is not in use.

    java -jar ./demo/build/libs/demo-1.0-SNAPSHOT.jar
    
#### 4. Check the Web-App**

Open your browser and visit:

    http://localhost:8080/?qId=1&uId=1 


## Build
In order to build this project, use [https://gradle.org/](gradle).

### Using the core Library
TODO

### Using the web Library
TODO


