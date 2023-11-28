package com.haileysun.bookmyhotel.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * The @Configuration and @Bean annotations are part of the Spring Framework,
 * which is used for Inversion of Control (IoC) and Dependency Injection in Java applications.
 *
 * @Configuration: This annotation indicates that the class is a source of bean definitions.
 * In other words, the ElasticsearchConfig class is a configuration class that
 * will provide instances of beans to be managed by the Spring container.
 * When the application context is loaded, Spring will scan this class for methods annotated with @Bean.
 *
 * @Value: This annotation is used to inject values into fields.
 * In this example, the values for elasticsearchAddress, elasticsearchUsername,
 * and elasticsearchPassword are injected from the application's configuration properties,
 * typically defined in a properties file or other external configuration sources.
 *
 * @Bean: The @Bean annotation is used on a method to define a bean.
 * The method elasticsearchClient is annotated with @Bean,
 * meaning that it returns an object that should be registered as a bean in the Spring application context.
 * The returned bean is a RestHighLevelClient, which is a client provided by Elasticsearch for
 * interacting with an Elasticsearch cluster through a high-level API.
 *
 * The RestHighLevelClient is more intuitive and user-friendly compared to the lower-level RestClient.
 * It's built on top of the RestClient and uses it internally to make HTTP requests to the Elasticsearch REST API.
 *
 * The bean named elasticsearchClient is created by instantiating a ClientConfiguration object with the server address and
 * credentials provided by the @Value injected fields. This configuration is then used to create and return a RestHighLevelClient object.
 *  - Since no name is specified within the @Bean annotation itself,
 *    Spring uses the method name elasticsearchClient as the bean's name.
 *    This name can then be used to reference this bean in other parts of the application,
 *    such as when you want to inject this bean into another component using @Autowired or other injection techniques provided by Spring.
 *
 * The RestClients.create() method takes the ClientConfiguration object to create a RestHighLevelClient instance.
 * This instance is then returned and managed by the Spring container, and can be injected into other parts of the application that require it.
 *
 * So, in summary,
 * @Configuration is used to declare a configuration class,
 * @Value is used to inject values into the class' fields, and
 * @Bean is used to declare a bean method, which when called,
 * will provide an instance of a bean (in this case, RestHighLevelClient) to
 * the Spring application context for dependency injection elsewhere in the application.
 */
@Configuration
public class ElasticsearchConfig {
    @Value("${elasticsearch.address}")
    private String elasticsearchAddress;
    @Value("${elasticsearch.username}")
    private String elasticsearchUsername;
    @Value("${elasticsearch.password}")
    private String elasticsearchPassword;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        /**
         * The ClientConfiguration object is created using the ClientConfiguration.builder() method and
         * setting the Elasticsearch server address and credentials using the connectedTo() and withBasicAuth() methods, respectively.
         */
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticsearchAddress)
                .withBasicAuth(elasticsearchUsername, elasticsearchPassword)
                .build();

        /**
         * The `RestClients.create()` method is called with the `ClientConfiguration` object as an argument to create a new `RestHighLevelClient` instance.
         * The `RestHighLevelClient` instance is returned by the `elasticsearchClient()` method and added to the Spring application context as a bean.
         */
        return RestClients.create(clientConfiguration).rest();
    }
}
