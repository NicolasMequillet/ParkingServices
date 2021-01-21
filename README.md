# REST API Parking

## Description
The main goal of this api is to sent to a client (mobile application, browser, ..) a list of available parkings arround a Gps position.

If the gsp position sent is contained in an area defined in the API
then the apporpriate service will be used to retrieve data

To call the Web Service Lacub used by the Bordeaux Metropole an arbitrary area was defined as:
* Latitude between: 44.774274 and 44.944685
* Longitude between: -0.701561 and -0.510386
 
Our test is based here on a call of a Web Service (Lacub) that returns data from the Bordeaux metropole
 
This is why the position of **the center of Bordeaux** was chosen for the performance of the tests:
* latitude=44.837789
* longitude=-0.579180

This API is composed of only one controller that allows to retrieve a list of parking closed to the gsp position sent by the caller.

## Download source
* git clone https://github.com/NicolasMequillet/ParkingServices.git
* Or download the zip file
 
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
* **java -jar ParkingApi/target/ParkingApi-0.0.1-SNAPSHOT.jar**

## End points
Context: /nm/parking/v1

### /list
* RequestParam: latitude (mandatory)
* RequestParam: longitude (mandatory)

Ex: <http://localhost:8080/nm/parking/v1/list?latitude=44.837789&longitude=-0.579180>

will return a list of Parking elements closed to the center of Bordeaux

##Project structure
This API is developed in JAVA and is based on the Spring Boot framework.
Only one end point is exposed.
The API WebFlux allows to call the Web service Lacub.
The bindings between the XML schema of the WS Lacub and the generated classes was realized with jaxb2-maven-plugin
The GeographicLib API allows to calcul distance between two GPS positions


##Notes

The distance calculation between 2 gps positions is linear. As part of a Professional API we could perhaps do a calculation on the driving distance

The authentication part has not been implemented in this API.



