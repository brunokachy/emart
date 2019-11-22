### emart
## A simple E-commerce site

Storage Solution used for the web service is h2 database

**h2 Database Details**

username=root

password=root

datasource url=jdbc:h2:mem:emartdb

H2 console is available at '/h2-console'


**Swagger UI was used for web service documentation**

Swagger UI is available at '/swagger-ui.html#'

**Authentication Method Proposed**

I propose the use of Secret Key for authentication. Each client is given a unique secret
key. The client calls a 'tokenize' endpoint with a secret key, 
a hashed token is generated with an expiration time and the client secret key. With the token, the client
can call any webservice endpoint with the token, on every endpoint service call, the token is checked for a valid secret key and expiration date.

With this authentication method. the webservice is secured from external or unauthorized calls. 
And client calls to webservice endpoint can be traced.

**How can you make the service redundant?**
This service can be made redundant by creating several instances of the service
and by load balancing. Creating several instances of the service makes the system to still be available on downtime. 
Load balancing spreads the load to several instances of the service during high traffic situation like 'Black Friday Sales' hence preventing a possible downtime.
