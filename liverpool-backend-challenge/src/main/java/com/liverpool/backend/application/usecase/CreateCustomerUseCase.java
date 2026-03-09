package com.liverpool.backend.application.usecase;

import com.liverpool.backend.application.dto.CreateCustomerRequest;
import com.liverpool.backend.application.dto.CustomerResponse;
import com.liverpool.backend.application.mapper.CustomerMapper;
import com.liverpool.backend.domain.exception.CustomerAlreadyExistsException;
import com.liverpool.backend.domain.model.Customer;
import com.liverpool.backend.domain.port.CustomerRepositoryPort;

/**
 * Creates a new customer in the system.
 *
 * @param request customer creation request
 * @return created customer response
 */
public class CreateCustomerUseCase {
  private final CustomerRepositoryPort customerRepositoryPort;

  public CreateCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
    this.customerRepositoryPort = customerRepositoryPort;
  }

  public CustomerResponse execute(CreateCustomerRequest request) {

    if (customerRepositoryPort.existsByUserId(request.getUserId()))
      throw new CustomerAlreadyExistsException(request.getUserId());

    Customer savedCustomer = customerRepositoryPort.save(CustomerMapper.toDomain(request));

    return CustomerMapper.toResponse(savedCustomer);
  }
}
