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
  (1) `@EnableWebSecurity`
      - When this annotation is used, Spring Security's web security configuration is enabled, which allows you to secure your application's endpoints using a variety of mechanisms such as form-based authentication, HTTP basic authentication, and OAuth2 authentication.
  (2) `@Configuration`
      - Indicates that the class is a configuration class that declares Spring beans.
  (3) `@Bean`
      - Indicates that a ***method*** is a provider of a bean instance for the Spring container. 
      - In Spring Framework, a bean is an object that is managed by the ***Spring IoC (Inversion of Control) container***, which provides various benefits such as dependency injection and lifecycle management.
      - By annotating a method with `@Bean`, we are telling the Spring container that the return value of that method is a bean that should be managed by the container. 
      - When the Spring container starts up, the container will then call the method to obtain an instance of the bean whenever the bean is needed by other parts of the application.
      ```Java
      @Bean
      public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
      }
      ```
      - In this case, the Spring container will create a new instance of BCryptPasswordEncoder and register it as a bean with the name passwordEncoder.
  (4) `@Autowired`
      - Automatically inject a dependency into a Spring bean.
      - ***Constructor injection** is generally considered to be the best practice for dependency injection in Spring.
      ```Java
      @Service
      public class MyService {

        private final MyRepository myRepository;

        @Autowired
        public MyService(MyRepository myRepository) {
            this.myRepository = myRepository;
        }
      }
      ```
  (5) `@PostMapping`
      - Equal to `@RequestMapping(method = RequestMethod. POST)`
      - Maps HTTP POST requests to a specific controller method.
        - `@PostMapping("/authenticate/host")`
  (6) `@RequestBody`
      - Indicates that the parameter of a controller method should be bound to the body of the HTTP request.
      - Spring will automatically ***deserialize*** the request body into an instance of the parameter type. 
      - This is useful when the HTTP request contains data in a specific format such as JSON, XML, or form data.
  (7) `@RestController`
      - Combines the `@Controller` and `@ResponseBody` annotations, making it easier to create RESTful web services.
      - Indicates that the class is a controller that handles HTTP requests and returns the response as a JSON, XML or any other representation.
  (8) 