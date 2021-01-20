# REST API Bordeaux Parking

## Definition
The main goal of this api is to sent to a client (mobile application, browser, ..) a list of available parkings in the Bordeaux city arround a Gps position.

The position of **the center of Bordeaux** was chosen for the performance of the tests:
* latitude=44.837789
* longitude=-0.579180

This API is composed of only one controller that allows:
* to retrieve a list of parking closed to the gsp position sent by the caller.
* to retrieve a list of parking with additional information such as hourly rate, place for bicycles, carpooling, ...

## Download source
git clone 

## Requirements to build API
* maven
* java 1.8

## Build
From the Parkservices directory, execute the command:
*  **mvn install**

## Properties
* The properties used here, like Lacub WS url, can be found in the file **application.yml**.

## Run
From the Parkservices directory, execute the command:
* **java -jar Bordeaux/target/Bordeaux-0.0.1-SNAPSHOT.jar**

## End points
Context: /nm/bod

### /parking
* RequestParam: latitude (mandatory)
* RequestParam: longitude (mandatory)

returns a list of Parking elements

Ex: <http://localhost:8080/nm/bod/parking?latitude=44.837789&longitude=-0.579180>

### /parking/all
* RequestParam: latitude (mandatory)
* RequestParam: longitude (mandatory)

returns a list of ParkingAll elements that contain a lot of information

Ex: <http://localhost:8080/nm/bod/parking/all?latitude=44.837789&longitude=-0.579180>

##Project structure


