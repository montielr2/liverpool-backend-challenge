package com.liverpool.backend.application.dto;

import java.util.List;
import lombok.Data;

@Data
public class CustomerResponse {
  private String userId;
  private String name;
  private String lastName;
  private String motherLastName;
  private String email;
  private String shippingAddress;
  private List<OrderResponse> orders;

  @Data
  public static class OrderResponse {
    private String orderRef;

    public OrderResponse(String orderRef) {
      this.orderRef = orderRef;
    }
  }
}
