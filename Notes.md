# Notes:
### How does Servlet interact with Controllers:
- In a Spring-based web application, a servlet is responsible for dispatching requests to the appropriate controller. 
- Here is an overview of how this process works:
  - When a client makes a request to a Spring MVC web application, the request is intercepted by the servlet container (e.g. Tomcat, Jetty).
  - The servlet container forwards the request to the DispatcherServlet, which is a special servlet provided by Spring that acts as a front controller for the application.
  - The DispatcherServlet examines the incoming request and determines which controller to invoke based on the request URL and the application's request mapping configuration.
  - The DispatcherServlet uses a HandlerMapping to look up the appropriate controller based on the request URL. The HandlerMapping is typically configured using Spring's @RequestMapping annotation or a dedicated XML configuration file.
  - Once the appropriate controller is identified, the DispatcherServlet invokes the appropriate controller method to handle the request.
  - The controller method can then use Spring's dependency injection framework to access other components, such as service classes or DAOs, that are needed to process the request.
  - The controller method processes the request and returns a ModelAndView object that encapsulates the response data and view name.
  - The DispatcherServlet uses a ViewResolver to look up the appropriate view based on the view name returned by the controller method.
  - Once the view is resolved, the DispatcherServlet invokes the view to generate the response content and send it back to the client.
- Overall, the servlet container and the DispatcherServlet work together to intercept incoming requests, determine which controller to invoke, and process the request and generate the response. Spring provides a number of abstractions and configuration options to make this process flexible and customizable.

### Annotations:
(1) @EnableWebSecurity
  - When this annotation is used, Spring Security's web security configuration is enabled, which allows you to secure your application's endpoints using a variety of mechanisms such as form-based authentication, HTTP basic authentication, and OAuth2 authentication.