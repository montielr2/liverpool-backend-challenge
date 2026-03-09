package com.liverpool.backend.domain.port;

import com.liverpool.backend.domain.model.Customer;
import java.util.Optional;

public interface CustomerRepositoryPort {
  Customer save(Customer customer);

  Optional<Customer> findByUserId(String userId);

  boolean existsByUserId(String userId);
}
