package com.liverpool.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String id;
    private String customerId;
    private String name;
    private String lastName;
    private String motherLastName;
    private String email;
    private String shippingAddress;
    private List<CustomerOrder> orders = new ArrayList<>();

    public void assignOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }
}
