package com.liverpool.backend.domain.port;

import com.liverpool.backend.domain.model.Order;

import java.util.List;

public interface OrdersProviderPort {
    List<String> findOrderRefsByUserId(String userId);
    List<Order> getOrders();
}
