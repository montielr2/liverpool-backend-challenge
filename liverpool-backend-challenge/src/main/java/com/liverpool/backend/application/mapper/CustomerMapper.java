package com.liverpool.backend.application.mapper;

import com.liverpool.backend.application.dto.CreateCustomerRequest;
import com.liverpool.backend.application.dto.CustomerResponse;
import com.liverpool.backend.domain.model.Customer;
import com.liverpool.backend.domain.model.CustomerOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerMapper {
    private CustomerMapper() {
    }

    public static Customer toDomain(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setCustomerId(request.getUserId());
        customer.setName(request.getName());
        customer.setLastName(request.getLastName());
        customer.setMotherLastName(request.getMotherLastName());
        customer.setEmail(request.getEmail());
        customer.setShippingAddress(request.getShippingAddress());
        customer.setOrders(new ArrayList<>());
        return customer;
    }

    public static CustomerResponse toResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setUserId(customer.getCustomerId());
        response.setName(customer.getName());
        response.setLastName(customer.getLastName());
        response.setMotherLastName(customer.getMotherLastName());
        response.setEmail(customer.getEmail());
        response.setShippingAddress(customer.getShippingAddress());
        response.setOrders(toOrderResponses(customer.getOrders()));
        return response;
    }

    private static List<CustomerResponse.OrderResponse> toOrderResponses(List<CustomerOrder> orders) {
        if (orders == null || orders.isEmpty())
            return Collections.emptyList();

        return orders.stream().map(order -> new CustomerResponse.OrderResponse(order.getOrderRef())).toList();
    }
}
