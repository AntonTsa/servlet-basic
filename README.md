<h1> Servlet </h1>

Created my first servlet TimeServlet that displays UTC current date and time.
Added Validate filter for timezone that checks if the timezone parameter is valid and if it's not -> returns response 
with status 400 (Bad Request) and message: "Invalid timezone".

To run the servlet on a local server, I used Apache Tomcat. Follow the steps below to set up and run the servlet:
1. Install Apache Tomcat on your local machine. You can download it from the [official website](https://tomcat.apache.org/download-90.cgi).
2. Copy the generated WAR file (myname.war) from the build/libs directory to the webapps folder of your Tomcat installation.
3. Start the Tomcat server by running the startup script located in the bin directory of your Tomcat installation.
4. Open a web browser and navigate to http://localhost:8080/time/ to see the current UTC date and time.
5. If you need to know date and time in a different timezone, you can pass the timezone as a query parameter. 
For example, to get the date and time in New York, use the URL: http://localhost:8080/time/?timezone=UTC+2
6. 
