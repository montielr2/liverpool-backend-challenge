package com.liverpool.backend.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderSearchResponse {
    private String orderRef;
    private String orderStatus;
    private String storeName;
    private List<OrderItemResponse> items;
}
