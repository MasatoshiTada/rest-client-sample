package com.example.restclientsample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClientBuilder() {
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(Duration.ofSeconds(5))  // 接続タイムアウト
                .withReadTimeout(Duration.ofSeconds(5));  // 読み取りタイムアウト
        RestClient.Builder builder = RestClient.builder()
                .defaultStatusHandler(
                        // ステータスコードが4xx・5xxの場合に例外が出ないようにする
                        status -> true,
                        (request, response) -> { /* 何もしない */ })
                .requestFactory(ClientHttpRequestFactories.get(settings));
        return builder;
    }

    @Bean
    public RestClient restClient(RestClient.Builder restClientBuilder, @Value("${hello-service.base-url}") String baseUrl) {
        RestClient restClient = restClientBuilder.baseUrl(baseUrl).build();  // テスト時に使われないように、baseUrlのみこちらで設定
        return restClient;
    }
}
