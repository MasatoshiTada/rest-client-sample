package com.example.restclientsample;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HelloClient {

    private final RestClient restClient;

    public HelloClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public HelloResponse getHello() {
        ResponseEntity<HelloResponse> responseEntity = restClient.get()
                .uri("/api/hello")
                .retrieve()
                .toEntity(HelloResponse.class);
        if (responseEntity.getStatusCode().isError()) {
            throw new RuntimeException("error");
        }
        return responseEntity.getBody();
    }

    public String postHello(HelloRequest request) {
        ResponseEntity<Void> responseEntity = restClient.post()
                .uri("/api/hello")
                .body(request)
                .retrieve()
                .toBodilessEntity();
        if (responseEntity.getStatusCode().isError()) {
            throw new RuntimeException("error");
        }
        return "OK";
    }
}
