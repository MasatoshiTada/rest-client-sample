package com.example.restclientsample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

@Configuration
public class TestConfig {

    @Bean
    public MockRestServiceServer mockRestServiceServer(RestClient.Builder builder) {
        // RestClient.BuilderのRequestFactoryを書き換え
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
        return server;
    }

    @Bean
    @Primary
    @DependsOn("mockRestServiceServer")  // mockRestServiceServer()が先に実行されるようにする
    public RestClient restClient(RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder.build();
        return restClient;
    }
}
