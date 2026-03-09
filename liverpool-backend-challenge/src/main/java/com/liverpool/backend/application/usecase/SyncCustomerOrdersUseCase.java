package com.liverpool.backend.application.usecase;

import com.liverpool.backend.application.dto.CustomerResponse;
import com.liverpool.backend.application.mapper.CustomerMapper;
import com.liverpool.backend.domain.exception.CustomerNotFoundException;
import com.liverpool.backend.domain.model.Customer;
import com.liverpool.backend.domain.model.CustomerOrder;
import com.liverpool.backend.domain.port.CustomerRepositoryPort;
import com.liverpool.backend.domain.port.OrdersProviderPort;
import java.util.List;

public class SyncCustomerOrdersUseCase {
  private final CustomerRepositoryPort customerRepositoryPort;
  private final OrdersProviderPort ordersProviderPort;

    /**
     * Synchronizes orders from the external service
     * and associates them with a customer.
     *
     * @param customerId customer identifier
     */
  public SyncCustomerOrdersUseCase(
      CustomerRepositoryPort customerRepositoryPort, OrdersProviderPort ordersProviderPort) {
    this.customerRepositoryPort = customerRepositoryPort;
    this.ordersProviderPort = ordersProviderPort;
  }

  public CustomerResponse execute(String userId) {
    Customer customer =
        customerRepositoryPort
            .findByUserId(userId)
            .orElseThrow(() -> new CustomerNotFoundException(userId));

    List<CustomerOrder> orders =
        ordersProviderPort.findOrderRefsByUserId(userId).stream().map(CustomerOrder::new).toList();
    customer.assignOrders(orders);
    Customer updatedCustomer = customerRepositoryPort.save(customer);

    return CustomerMapper.toResponse(updatedCustomer);
  }
}
