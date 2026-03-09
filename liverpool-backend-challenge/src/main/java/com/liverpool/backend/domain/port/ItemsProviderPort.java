package com.liverpool.backend.domain.port;

import com.liverpool.backend.domain.model.OrderItem;

import java.util.List;

public interface ItemsProviderPort {
    List<OrderItem> getItems();
}
