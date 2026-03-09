package com.liverpool.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private String id;
  private String orderRef;
  private String orderStatus;
  private String storeName;
  private String itemId;
}
