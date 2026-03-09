package com.liverpool.backend.application.usecase;

import com.liverpool.backend.application.dto.CustomerResponse;
import com.liverpool.backend.application.dto.UpdateCustomerRequest;
import com.liverpool.backend.application.mapper.CustomerMapper;
import com.liverpool.backend.domain.exception.CustomerNotFoundException;
import com.liverpool.backend.domain.model.Customer;
import com.liverpool.backend.domain.port.CustomerRepositoryPort;

import java.util.Optional;

public class UpdateCustomerUseCase {
    private final CustomerRepositoryPort customerRepositoryPort;

    public UpdateCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    public CustomerResponse execute(String userId, UpdateCustomerRequest request) {
        //Customer customer = customerRepositoryPort.findByUserId(userId).orElseThrow(() -> new CustomerNotFoundException(userId));
        Optional<Customer> customerOptional = customerRepositoryPort.findByUserId(userId);

        if (customerOptional.isEmpty())
            throw new CustomerNotFoundException(userId);

        Customer customer = customerOptional.get();
        customer.setName(request.getName());
        customer.setLastName(request.getLastName());
        customer.setMotherLastName(request.getMotherLastName());
        customer.setEmail(request.getEmail());
        customer.setShippingAddress(request.getShippingAddress());

        Customer updatedCustomer = customerRepositoryPort.save(customer);

        return CustomerMapper.toResponse(updatedCustomer);
    }

}
