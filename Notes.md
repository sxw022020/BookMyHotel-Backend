# Notes:
## How does Servlet interact with Controllers:
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

## Spring Security Filter:
- Spring Security is a powerful and highly customizable authentication and access control framework for Java applications. 
- One of the core components of Spring Security is its filter chain, which is a series of Servlet filters responsible for handling various aspects of authentication, authorization, and security-related tasks.
- When a request comes into a Spring Security-enabled application, it passes through a series of filters in the filter chain. 
- Each filter is responsible for a specific security task and processes the request accordingly. 
- If any filter in the chain determines that the request is not allowed or not authenticated, it can either redirect the user to an authentication or error page or return an HTTP error response.
- These are just some examples of the filters available in Spring Security. 
- The framework allows you to configure the filter chain according to your specific requirements, and you can even create custom filters to handle unique security scenarios. 
- The order in which filters are defined in the chain is important, as it determines the sequence in which the filters process the incoming requests.
- To configure the Spring Security filter chain, you can use:
  - (1) Java-based configuration with the @EnableWebSecurity annotation
    - `@EnableWebSecurity`
      - Enable web security features for a Java-based application.
      - By adding this annotation to a class that extends WebSecurityConfigurerAdapter, you can configure and customize various aspects of Spring Security, such as authentication, authorization, and filter chain setup.
      - Classes annotated with `@EnableWebSecurity` are treated as ***configuration classes*** by Spring.
  - (2) XML-based configuration with the <http> element.

## Why NOT need to include `aws-rds` in pom.xml for mysql connection?
- AWS RDS is a managed database service provided by Amazon Web Services, which means it is not a library or dependency that you need to include in your project.
- AWS RDS is essentially a platform that provides you with a managed MySQL database instance that you can connect to using standard MySQL drivers and APIs. 
- When you create an RDS instance, AWS takes care of the underlying infrastructure and configuration for you, including installing and configuring the MySQL database engine.
- ***To connect to an RDS MySQL instance from your Spring Boot application***, you only need to include the `MySQL driver` as a dependency in your pom.xml file, and then configure the connection parameters for your RDS instance in your application's configuration file.

## Why need to include `aws-s3` in pom.xml for operations between Spring Boot and AWS S3?
- `aws-java-sdk-s3`  includes the classes and methods necessary to interact with AWS S3.
- You will also need to provide AWS credentials to your Spring Boot application in order to access your S3 bucket.

## Composite Primary Key in HPA and Hibernate:
- [Link](https://hellokoding.com/composite-primary-key-in-jpa-and-hibernate/)

## Security Group and RDS-MySQL
- (1) Create a security group with only allowing requests for PORT 3306 (default port for MySQL)
  - 3306 is a default port num for MySQL, if the request has request for 3306, 
    that means it 
- (2) Apply (1)'s security group to RDS-MySQL

## Spring Security:
1. `Princile`
   - 

## Annotations:
1. `@EnableWebSecurity`
    - When this annotation is used, Spring Security's web security configuration is enabled, which allows you to secure your application's endpoints using a variety of mechanisms such as form-based authentication, HTTP basic authentication, and OAuth2 authentication.
2. `@Configuration`
    - Indicates that the class is a configuration class that declares Spring beans.
3. `@Bean`
    - Indicates that a ***method*** is a provider of a bean instance for the Spring container. 
    - In Spring Framework, a bean is an object that is managed by the ***Spring IoC (Inversion of Control) container***, which provides various benefits such as dependency injection and lifecycle management.
    - By annotating a method with `@Bean`, we are telling the Spring container that the return value of that method is a bean that should be managed by the container. 
    - When the Spring container starts up, the container will then call the method to obtain an instance of the bean whenever the bean is needed by other parts of the application.
    ```Java
    public class SecurityConfig extends WebSecurityConfigurerAdapter { 
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
    ```
   - In this case, the Spring container will create a new instance of BCryptPasswordEncoder and register it as a bean with the name passwordEncoder.
4. `@Autowired`
    - Automatically inject a dependency into a Spring bean.
    - ***Constructor injection*** is generally considered to be the best practice for dependency injection in Spring.
        - ***Why Constructor injection is preferred?***
           - **Clear Dependencies**: 
             - Constructor injection makes the dependencies of a class explicit by requiring them to be passed as parameters to the constructor. 
             - This makes it easier to understand the dependencies of a class and reduces the likelihood of accidentally missing a dependency.
           - **Testability**: 
             - Constructor injection makes it easier to write unit tests for a class because it allows the dependencies to be easily mocked or replaced with test doubles. 
             - This can make it easier to isolate the class being tested and make the tests more reliable.
    ```Java
    @Service
    public class MyService {
        private final MyRepository myRepository;
        
        // constructor injection
        @Autowired
        public MyService(MyRepository myRepository) {
            this.myRepository = myRepository;
        }
    }
    ```
5. `@PostMapping("url_path")`
    - Equal to `@RequestMapping(method = RequestMethod. POST)`
    - Maps HTTP POST requests to a specific controller method.
        - `@PostMapping("/authenticate/host")`
6. `@RequestBody("url_path")`
    - Indicates that the parameter of a controller method should be bound to the body of the HTTP request.
    - Spring will automatically ***deserialize*** the request body into an instance of the parameter type. 
    - This is useful when the HTTP request contains data in a specific format such as JSON, XML, or form data.
7. `@RestController`
    - Combines the `@Controller` and `@ResponseBody` annotations, making it easier to create RESTful web services.
      - `@ResponseBody`
        - Indicates that the User object should be extracted from the request body. 
    - Indicates that the class is a controller that handles HTTP requests and returns the response as a JSON, XML or any other representation.
    - If only use `@Controller`, you need to add `@ResponseBody` annotation to each of Controller method if you want to return a JSON or XML response.
8. `@Entity`
    - Marks a Java class as a persistent entity, meaning that it can be mapped to a corresponding table in a relational database.
9. `@Table(name = "table_name")`
    - Table name as "xxx" for a persistent entity.
10. `@Id`
    - Defines primary key of a persistent entity.
      - `@GeneratedValue(strategy = xxx)`
        - JPA provider will generate the primary key value based on assigned "strategy"
    - `@EmbeddedId`
      - ***Note:*** the `@EmbeddedId` annotation is used in conjunction with the `@Embeddable` annotation, which is used to specify that a class represents a composite primary key. The `@Embeddable` annotation is used on the class that represents the composite primary key.
      - `@Embeddable`
        - An embeddable class is a value type that can be embedded in other entities or value types.
11. `@JsonProperty("custom_col_name")`
    - Specifies the names of the JSON properties that are returned to the response.
    - If you use ***Builder Pattern*** as a constructor, put `@JsonProperty("custom_col_name")` in Builder class.
12. `@JsonIgnore`
    - A Jackson annotation used in Java.
    - Indicates that a particular field or property of a Java class should be ignored during serialization and deserialization.
      - ***Serialization***:
        - The process of converting a Java object into a JSON representation,
      - ***Deserialization***:
        - The process of converting JSON into a Java object.
    - When `@JsonIgnore` is added to a field or property in a Java class, Jackson will not include that field or property in the JSON output during serialization. 
    - Similarly, when deserializing JSON input into a Java object, Jackson will ignore the JSON field that corresponds to the ignored Java field or property.
13. `@JsonDeserialize(xxx)`
    - Specifies a custom deserializer for a Java ***object field*** or ***setter method***.
    ```Java
    public class User {

        private String username;

        // Way 1 - field
        // the "password" field should be deserialized using the PasswordDeserializer class
        @JsonDeserialize(using = PasswordDeserializer.class)
        private Password password;

        // Or
    
        // Way 2 - setter
        @JsonDeserialize(using = PasswordDeserializer.class)
        public void setPassword(Password password) {
            this.password = password;
        }
    }

    // must implement the `JsonDeserializer interface` and 
    // override the `deserialize()` method to perform the custom deserialization logic
    public class PasswordDeserializer extends JsonDeserializer<Password> {

        @Override
        public Password deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            // Perform custom deserialization logic
            return new Password(jp.getText());
        }
    }
    ```
    - When a Java class field or setter method is annotated with @JsonDeserialize, it indicates that the corresponding JSON property should be deserialized using a custom deserializer class instead of the default deserialization mechanism provided by Jackson.
    - When combining with ***Builder Pattern***:
    ```Java
    @JsonDeserialize(builder = User.Builder.class)
    public class User implements Serializable {
        ///...
    }
    ```
14. `@ControllerAdvice`
    - Adds for a class.
    - Indicates that the class is an exception handler class that applies to all controllers in the application.
    - `@ExceptionHandler(exceptionClassName.class)`
      - Adds for a method in an exception handler class.
      - Indicates that a method should be invoked when a specific exception is thrown.
      - See "/exception/CustomExceptionHandler"
15. `@Transactional(xxx)`
    - Transactions ensure that a group of database operations are executed atomically, meaning that they are either all committed to the database or all rolled back in case of an error.
    - `@Transactional(isolation = Isolation.SERIALIZABLE)`
      - The isolation parameter specifies the isolation level of the transaction and describes how changes applied by concurrent transactions are visible to each other.
      - `Isolation` level defines how the database handles concurrent transactions that access the same data.
      - `SERIALIZABLE` is the highest level of isolation. It prevents all mentioned concurrency side effects, but can lead to the lowest concurrent access rate because it executes concurrent calls sequentially. In other words, concurrent execution of a group of serializable transactions has the same result as executing them in serial.
      - `isolation = Isolation.SERIALIZABLE` indicates that the transaction should be executed in a way that ensures that the result is the same as if the transactions were executed one after the other, in a serialized manner. 
      - This level provides the highest level of data consistency, but it can also lead to reduced concurrency and increased locking.
16. `@Value("{xxx}")`
    - Inject values into fields or constructor parameters. 
      ```java
        @Component
        public class MyClass {
    
            // 1. field
            @Value("${my.property}")
            private String myProperty;
    
            // 2. constructor param
            private final String myProperty;

            @Autowired
            public MyClass(@Value("${my.property}") String myProperty) {
                this.myProperty = myProperty;
            }
        }
      ```
    - It is typically used to inject configuration properties or other values into Spring-managed beans.
17. `@ManyToOne`
    - Defines a many-to-one relationship between two entities.
    - This annotation is used on the child entity ("Many") to specify that it has a many-to-one relationship with a parent entity ("One").
    - ***Many***: current class (i.e. current entity)
    - ***One***: another class (i.e. another entity) which is annotated by `@ManyToOne`
    - `@JoinColumn(name = "xxx", referencedColumnName = "xxx")`
      - Joins two tables in a many-to-one or one-to-one relationship.
      - `name`
        - specifies name of ***foreign key*** in current class (i.e. current entity)
      - `referencedColumnName`
        - specifies the name of the primary key column in another class (i.e. another entity)
        - If the `referencedColumnName` attribute is not specified, Spring framework will use the default primary key column name of the referenced entity.
      - ***Note:***
        - If the `@JoinColumn` annotation is not used, Spring framework will generate a default foreign key column name based on the name of the field or property that represents the relationship.
    - `@MapsId`
      - Maps a child entity's primary key to its parent entity's primary key.
    ```java
    public class Stay {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long stay_id;
    
        // Many: Stay
        // One: User
        @ManyToOne
        // foreign key in Stay: username
        // 
        @JoinColumn(name = "user_id")
        private User host;
    
        // ...
    }

    ```
18. `@OneToMany(mappedBy = "xxx", cascade = CascadeType.xxx, fetch = FetchType.xxx)`
    - Defines a one-to-many relationship between two entities.
    - This annotation is used on the parent entity ("One") to specify that it has a one-to-many relationship with one or more child entities ("Many")
    - ***It's important to note that the child entity ("Many") must have a corresponding relationship mapping (i.e. `@ManyToOne`) on its side.***
    - `mappedBy` 
      - Specifies the name of the field or property in the child entity that owns the relationship.
    - `cascade` 
      - Specifies that changes to the parent entity should cascade to the child entities.
    - `fetch = FetchType.LAZY` 
      - Specifies that a related entity should not be loaded from the database until it is explicitly accessed. 
    - `fetch = FetchType.EAGER`
      - Specifies that a related entity should be loaded immediately along with the parent entity.