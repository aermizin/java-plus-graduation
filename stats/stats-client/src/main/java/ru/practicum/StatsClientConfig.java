package ru.practicum;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class StatsClientConfig {

    @Bean
    @LoadBalanced
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://stats-server")
                .build();
    }
}