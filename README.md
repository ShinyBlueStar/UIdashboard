UIDashboard Project

It serves as a backend for monitoring tasks that are generated automatically and saved in a database. This project is written with Spring Boot, Spring MVC, and the Java programming language. Additionally, 
it uses the H2 database to save all generated tasks. In the next step, there is a RESTful API serving the frontend.

As I mentioned, having an MVC architecture not only makes it easier to understand and maintain, but also prepares it for required changes in the future. It has three layers: data model, service, and controller, 
which prevent the controller and data model from connecting directly. Furthermore, even just DTOs are transmitted to the controller and to the frontend as objects thanks to Mappers.

In the section responsible for sending responses to the frontend, JSON objects are transferred as they are lightweight and there are standards for sending and receiving data in this way.

Regarding the functionality of the code: at first, when the project starts, four random tasks are created and added to the database. These tasks have random times, statuses, etc., utilizing an EventListener. Every minute,
a scheduler runs and updates all data in the database in a random manner, except for those that have finished or terminated statuses. To minimize the cost of fetching from the database, these tasks have "IsDone" flags,
preventing them from being chosen during the update process, as well as during the creation of new tasks.

In terms of design patterns, the project follows the SOLID design principles, and all classes inherit from their base classes or implement their base interfaces, with these parent interfaces linked at the base level.

In regards to Docker and Nginx concepts, my design is defined in a Docker Compose file, and you can see that there are two instances of the project behind an Nginx server, which connects to the frontend.

Build:
To build, you can run docker compose up --build. It depends on the Nginx Docker image and the H2 Docker image. At the end, you can send your request to http://localhost:9092/task/all. You can test the backend part 
using Postman or Httpie. Additionally, you can run this project locally if you have problems with Docker, but both the Docker part and the functionality of the project work correctly.

DashboardFrontend Project:

This project services the user and provides data every minute from the backend. Therefore, it is essential to ensure that the backend service is running. The frontend project should be cloned 
from https://github.com/fatemehsadeghi/dashboardfrontend.git. It is written with AngularJS and uses the TypeScript programming language. It is a lightweight project with separate sections such as
services, entities, and the controller part, and it is accessible in the browser at the address http://localhost:4200.

I strive to run this project on Docker and add an Nginx web server to serve as an intermediary between the frontend and the user. However, in the last few hours, I encountered some errors and 
did not finish this deployment section. Nevertheless, it still works correctly on your local address; you just need to run the command ng build followed by ng serve. When the project starts,
it first sends a request to the backend (with Nginx set in front of the backend as a load balancer), and subsequently sends requests every minute.
