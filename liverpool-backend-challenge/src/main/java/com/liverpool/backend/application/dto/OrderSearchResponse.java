package com.liverpool.backend.application.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderSearchResponse {
  private String orderRef;
  private String orderStatus;
  private String storeName;
  private List<OrderItemResponse> items;
}
