# MyService is a service that accesses the exchange rate service, and gives a gif in response.
***
If the exchange rate against USD for today has become higher than yesterday, then a random gif from the "Rich" category
comes, if the rate has become lower, then a random gif from the "broken" category comes.
***
The parameters are placed in the settings in the MyData.properties file;
Communication with the Api for obtaining exchange rates is carried out by the key rate_api_key;
Communication with the Api for getting gifs is carried out by the gif_api_key key;

Gradle is used to build the project.
***
To run in Docker, you need to run Dockerfile.
