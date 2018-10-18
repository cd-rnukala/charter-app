Here is how tested the apis:

a) Display List of All Existing Planes 

curl 'http://localhost:8080/charter-app/data/planes'

Response:
[{"id":101,"name":"AirPartner","airSpeed":300.0,"range":10000.0},{"id":201,"name":"MillionAir","airSpeed":600.0,"range":15000.0},{"id":301,"name":"Belavia","airSpeed":1000.0,"range":25000.0}]

b) load planes into the system

curl -H "Content-Type: application/json" -X POST -d '[{"id":"144","name":"DeltaAirlines","airSpeed":"400", "range":"1000"},{"id":"147","name":"AmericanAirways","airSpeed":"250", "range":"500"}]' http://localhost:8080/charter-app/data/planes

Response:
[{"id":101,"name":"AirPartner","airSpeed":300.0,"range":100.0},{"id":201,"name":"MillionAir","airSpeed":600.0,"range":150.0},{"id":301,"name":"Belavia","airSpeed":1000.0,"range":250.0},{"id":147,"name":"AmericanAirways","airSpeed":250.0,"range":500.0},{"id":144,"name":"DeltaAirlines","airSpeed":400.0,"range":1000.0}]


c) retrieve a plane from the system 

Request: curl 'http://localhost:8080/charter-app/data/planes/101'

Response:
{"id":101,"name":"AirPartner","airSpeed":300.0,"range":10000.0}

d) For a given plane, compute the time it takes to fly from one location to another (assuming that it takes 30 minutes for each refueling stop). For specifying locations, use a simple, flat 2 dimensional coordinate space.

Request: 
curl -H "Content-Type: application/json" -X POST -d '{"fromLocation":"SFO","toLocation":"PDX", "planeName":"AirPartner"}' http://localhost:8080/charter-app/data/plane/calculate

Response:
{"fromLocation":"SFO","toLocation":"PDX","planeName":"AirPartner","totalTimeTaken":4.588801913972799} 
totalTimeTaken is in Hours