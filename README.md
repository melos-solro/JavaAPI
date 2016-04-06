# Routru API Usage

Note: This is for development usage only. This program cannot be distributed or modified in any other means.

## Version 0.1

This version includes the /searchPlaces API Endpoint

## Instructions to run

java -jar RoutruEnterprise-0.1.jar

After running the executable go to http://localhost:8080/searchPlaces?location=Baltimore&query=Chipotle

## Port Info

The default port is 8080, so all requests are made over localhost:8080 or 127.0.0.1:8080

## Endpoints

### /searchPlaces

Before State: User passes in valid parameters to the API endpoint. 

Parameters:

- location (Required) : pass in a text location query (longitude latitude is not released in this version)

- query (Required) : pass in a query for a place for example: "indian restaurants"

- type (Optional) : pass in a type as defined by Google Places Types

Processing: All data is queried from the Google Places API

Return State: JSON with the data

After State: User can view the correct results based on the parameters they put in