package com.liverpool.backend.infrastructure.client.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderResponse {
  private String orderRef;
  private String userId;
  private String canal;
  private String orderStatus;
  private Boolean marketPlace;
  private Boolean giftRegistry;
  private List<String> items;
  private String storeName;
  private String id;
}
