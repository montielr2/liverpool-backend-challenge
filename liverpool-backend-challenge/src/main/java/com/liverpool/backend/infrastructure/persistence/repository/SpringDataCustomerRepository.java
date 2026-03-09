package com.liverpool.backend.infrastructure.persistence.repository;

import com.liverpool.backend.infrastructure.persistence.document.CustomerDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataCustomerRepository extends MongoRepository<CustomerDocument, String> {
  Optional<CustomerDocument> findByUserId(String userId);

  boolean existsByUserId(String userId);
}
