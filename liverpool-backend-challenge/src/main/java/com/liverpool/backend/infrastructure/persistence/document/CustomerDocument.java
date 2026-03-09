package com.liverpool.backend.infrastructure.persistence.document;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "customers")
public class CustomerDocument {
  @Id private String id;
  private String userId;
  private String name;
  private String lastName;
  private String motherLastName;
  private String email;
  private String shippingAddress;
  private List<OrderDocument> orders = new ArrayList<>();

  @Data
  public static class OrderDocument {
    private String orderRef;

    public OrderDocument() {}

    public OrderDocument(String orderRef) {
      this.orderRef = orderRef;
    }
  }
}
