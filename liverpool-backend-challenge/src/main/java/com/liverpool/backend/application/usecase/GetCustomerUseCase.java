package com.liverpool.backend.application.usecase;

import com.liverpool.backend.application.dto.CustomerResponse;
import com.liverpool.backend.application.mapper.CustomerMapper;
import com.liverpool.backend.domain.exception.CustomerNotFoundException;
import com.liverpool.backend.domain.model.Customer;
import com.liverpool.backend.domain.port.CustomerRepositoryPort;
import java.util.Optional;

/**
 * Retrieves a customer by its identifier.
 *
 * @param id customer identifier
 * @return customer response
 */
public class GetCustomerUseCase {
  private final CustomerRepositoryPort customerRepositoryPort;

  public GetCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
    this.customerRepositoryPort = customerRepositoryPort;
  }

  public CustomerResponse execute(String userId) {
    Optional<Customer> customerOptional = customerRepositoryPort.findByUserId(userId);

    if (customerOptional.isEmpty()) throw new CustomerNotFoundException(userId);

    Customer customer = customerOptional.get();
    return CustomerMapper.toResponse(customer);
  }
}
