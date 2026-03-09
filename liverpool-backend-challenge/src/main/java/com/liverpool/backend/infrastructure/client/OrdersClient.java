package com.liverpool.backend.infrastructure.client;

import com.liverpool.backend.domain.exception.ExternalServiceException;
import com.liverpool.backend.domain.port.OrdersProviderPort;
import com.liverpool.backend.infrastructure.client.dto.OrderResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class OrdersClient implements OrdersProviderPort {

    private final RestClient restClient;

    public OrdersClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://6994a4eab081bc23e9c0f61e.mockapi.io/api/v1").build();
    }

    @Override
    public List<String> findOrderRefsByUserId(String userId) {
        try {
            List<OrderResponse> orders = restClient.get().uri("/pedidos").retrieve().body(new ParameterizedTypeReference<List<OrderResponse>>() {});

            if(orders == null || orders.isEmpty())
                return Collections.emptyList();

            List<String> result = new ArrayList<>();

            for(OrderResponse order : orders) {

                if(!userId.equals(order.getUserId()))
                    continue;

                String orderRef = order.getOrderRef();

                if(!result.contains(orderRef))
                    result.add(orderRef);
            }

            return result;
        } catch (RestClientException ex) {
            throw new ExternalServiceException("Error retrieving orders from external service", ex);
        }
    }
}
