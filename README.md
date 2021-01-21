# Parking REST API

## Description
The main goal of this api is to sent to a client (mobile application, browser, ..) a list of available parkings arround a Gps position.

This API is in test version and is based on a call to a Web Service (Lacub) that returns data about parkings from the Bordeaux metropole.
 
If the gsp position sent is contained in an area defined in the API
then the apporpriate service will be used to retrieve data.

To call the Web Service Lacub used by the Bordeaux Metropole an arbitrary area was defined with:
* Latitude between: 44.774274 and 44.944685.
* Longitude between: -0.701561 and -0.510386.
 
If the gps position sent is in this range then the API will call the Lacub WS and return data.

For more relevance, the list of Parkings returned by the API are sorted:
* by the distance in Km from the gps position (in asc order).
* and by the number of parking space available (in desc order).

So in the same Km, the API will first display the car parks with the most available parking spaces.

Items with the tag **\<bm:ETAT\>FERME\</bm:ETAT\>** will be filtered.

Only parkings with the status Open will be displayed. 

## Download source
* git clone https://github.com/NicolasMequillet/ParkingServices.git
* Or download the zip file.
 
## Requirements to build API
* maven
* java 1.8

## Build
From the root directory, execute the command:
*  **mvn install**

## Properties
* The properties used here, like Lacub WS url, can be found in the file **application.yml**.

## Run
From the root directory, execute the command:
* **java -jar ParkingApi/target/ParkingApi-0.0.1-SNAPSHOT.jar**

## Test
* Context: /nm/parking/v1
* End point: /list
* RequestParam: latitude (mandatory)
* RequestParam: longitude (mandatory)

Ex: <http://localhost:8080/nm/parking/v1/list?latitude=44.837789&longitude=-0.579180>

will return a list of Parking elements near to the center of Bordeaux.

## Project structure
This API is developed in JAVA and is based on the Spring Boot framework.
Only one end point is exposed.
The API WebFlux allows to call the Web service Lacub.
The bindings between the XML schema of the WS Lacub and the generated classes was realized with jaxb2-maven-plugin
The GeographicLib API allows to calcul distance between two GPS positions

## Notes

The distance calculation between 2 gps positions is linear. 

In the context of a professional API we could perhaps do a calculation on the distance taking into account the traffic and the direction of traffic.

The authentication part has not been implemented in this API.



