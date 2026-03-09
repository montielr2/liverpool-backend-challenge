package com.liverpool.backend.application.usecase;

import com.liverpool.backend.application.dto.OrderSearchResponse;
import com.liverpool.backend.domain.port.ItemsProviderPort;
import com.liverpool.backend.domain.port.OrdersProviderPort;
import com.liverpool.backend.domain.port.SearchOrdersPort;
import com.liverpool.backend.domain.service.OrderSearchDomainService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SearchOrdersUseCase implements SearchOrdersPort {

  private final OrdersProviderPort ordersProviderPort;
  private final ItemsProviderPort itemsProviderPort;
  private final OrderSearchDomainService orderSearchDomainService;

    /**
     * Searches orders for a given customer using filters.
     *
     * @param request search parameters
     * @return list of matching orders
     */
  public SearchOrdersUseCase(
      OrdersProviderPort ordersProviderPort,
      ItemsProviderPort itemsProviderPort,
      OrderSearchDomainService orderSearchDomainService) {
    this.ordersProviderPort = ordersProviderPort;
    this.itemsProviderPort = itemsProviderPort;
    this.orderSearchDomainService = orderSearchDomainService;
  }

  @Override
  public List<OrderSearchResponse> search(String query) {
    return orderSearchDomainService.search(
        ordersProviderPort.getOrders(), itemsProviderPort.getItems(), query);
  }
}
