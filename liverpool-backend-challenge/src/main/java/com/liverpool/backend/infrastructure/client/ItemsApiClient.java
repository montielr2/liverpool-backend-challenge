package com.liverpool.backend.infrastructure.client;

import com.liverpool.backend.domain.model.OrderItem;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class ItemsApiClient {
    private final RestClient restClient;

    public ItemsApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<OrderItem> getItems() {
        return restClient.get()
                .uri("https://6994a4eab081bc23e9c0f61e.mockapi.io/api/v1/items")
                .retrieve()
                .body(new ParameterizedTypeReference<List<OrderItem>>() {});
    }
}
