package com.liverpool.backend.infrastructure.persistence.adapter;

import com.liverpool.backend.domain.model.OrderItem;
import com.liverpool.backend.domain.port.ItemsProviderPort;
import com.liverpool.backend.infrastructure.client.ItemsApiClient;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ItemsProviderAdapter implements ItemsProviderPort {
  private final ItemsApiClient itemsApiClient;

  public ItemsProviderAdapter(ItemsApiClient itemsApiClient) {
    this.itemsApiClient = itemsApiClient;
  }

  @Override
  public List<OrderItem> getItems() {
    return itemsApiClient.getItems();
  }
}
