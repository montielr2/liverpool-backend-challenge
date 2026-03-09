package com.liverpool.backend.infrastructure.persistence.adapter;

import com.liverpool.backend.domain.model.Customer;
import com.liverpool.backend.domain.model.CustomerOrder;
import com.liverpool.backend.domain.port.CustomerRepositoryPort;
import com.liverpool.backend.infrastructure.persistence.document.CustomerDocument;
import com.liverpool.backend.infrastructure.persistence.repository.SpringDataCustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {
    private final SpringDataCustomerRepository repository;

    public CustomerRepositoryAdapter(SpringDataCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerDocument document = toDocument(customer);
        CustomerDocument savedDocument = repository.save(document);
        return toDomain(savedDocument);
    }

    @Override
    public Optional<Customer> findByUserId(String userId) {
        return repository.findByUserId(userId).map(this::toDomain);
    }

    @Override
    public boolean existsByUserId(String userId) {
        return repository.existsByUserId(userId);
    }

    private CustomerDocument toDocument(Customer customer) {
        CustomerDocument document = new CustomerDocument();
        document.setId(customer.getId());
        document.setUserId(customer.getCustomerId());
        document.setName(customer.getName());
        document.setLastName(customer.getLastName());
        document.setMotherLastName(customer.getMotherLastName());
        document.setEmail(customer.getEmail());
        document.setShippingAddress(customer.getShippingAddress());
        document.setOrders(customer.getOrders().stream().map(order -> new CustomerDocument.OrderDocument(order.getOrderRef())).toList());

        return document;
    }

    private Customer toDomain(CustomerDocument document) {
        Customer customer = new Customer();
        customer.setId(document.getId());
        customer.setCustomerId(document.getUserId());
        customer.setName(document.getName());
        customer.setLastName(document.getLastName());
        customer.setMotherLastName(document.getMotherLastName());
        customer.setEmail(document.getEmail());
        customer.setShippingAddress(document.getShippingAddress());
        customer.setOrders(document.getOrders().stream().map(order -> new CustomerOrder(order.getOrderRef())).toList());

        return customer;
    }
}
