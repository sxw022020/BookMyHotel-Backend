package com.haileysun.bookmyhotel.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * The ElasticsearchConfig class uses the Spring `@Configuration` annotation to indicate that it provides bean definitions to the Spring application context.
 */
@Configuration
public class ElasticsearchConfig {
    @Value("${elasticsearch.address}")
    private String elasticsearchAddress;
    @Value("${elasticsearch.username}")
    private String elasticsearchUsername;
    @Value("${elasticsearch.password}")
    private String elasticsearchPassword;

    /**
     *  `@Bean`: indicating that it creates a bean instance of the `RestHighLevelClient` class, named `elasticsearchClient`,
     *  that can be used in other parts of the application.
     *
     *  - RestHighLevelClient is a Java client provided by the Elasticsearch Java High-Level REST client library that provides a higher-level, more intuitive API for interacting with an Elasticsearch cluster compared to the lower-level RestClient.
     *  - The RestHighLevelClient uses the RestClient internally to perform HTTP requests to the Elasticsearch REST API.
     */
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
