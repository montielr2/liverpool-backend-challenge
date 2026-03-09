package com.liverpool.backend.domain.service;

import com.liverpool.backend.application.dto.OrderItemResponse;
import com.liverpool.backend.application.dto.OrderSearchResponse;
import com.liverpool.backend.domain.model.Order;
import com.liverpool.backend.domain.model.OrderItem;
import com.liverpool.backend.util.TextNormalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Domain service responsible for applying business rules
 * when searching customer orders.
 */
@Service
public class OrderSearchDomainService {
  public List<OrderSearchResponse> search(List<Order> orders, List<OrderItem> items, String query) {
    String normalizedQuery = TextNormalizer.normalize(query);

    Map<String, OrderItem> itemsById =
        items.stream().collect(Collectors.toMap(OrderItem::getId, item -> item, (a, b) -> a));

    return orders.stream()
        .map(order -> toResponse(order, itemsById.get(order.getItemId())))
        .filter(response -> matches(response, normalizedQuery))
        .collect(Collectors.toList());
  }

  private OrderSearchResponse toResponse(Order order, OrderItem item) {
    OrderItemResponse itemResponse = new OrderItemResponse();
    if (item != null) {
      itemResponse.setItemId(item.getId());
      itemResponse.setDisplayName(item.getDisplayName());
    }

    OrderSearchResponse response = new OrderSearchResponse();
    response.setOrderRef(order.getOrderRef());
    response.setOrderStatus(order.getOrderStatus());
    response.setStoreName(order.getStoreName());
    response.setItems(item != null ? List.of(itemResponse) : Collections.emptyList());

    return response;
  }

  private boolean matches(OrderSearchResponse order, String normalizedQuery) {
    List<String> fields = new ArrayList<>();
    fields.add(TextNormalizer.normalize(order.getOrderRef()));
    fields.add(TextNormalizer.normalize(order.getOrderStatus()));
    fields.add(TextNormalizer.normalize(order.getStoreName()));

    if (order.getItems() != null) {
      order.getItems().forEach(item -> fields.add(TextNormalizer.normalize(item.getDisplayName())));
    }

    return fields.stream().anyMatch(field -> field.contains(normalizedQuery));
  }
}
