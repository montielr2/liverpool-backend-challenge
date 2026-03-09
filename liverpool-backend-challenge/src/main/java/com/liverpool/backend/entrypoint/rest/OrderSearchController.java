package com.liverpool.backend.entrypoint.rest;

import com.liverpool.backend.application.dto.OrderSearchResponse;
import com.liverpool.backend.domain.port.SearchOrdersPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderSearchController {
    private final SearchOrdersPort searchOrdersPort;

    public OrderSearchController(SearchOrdersPort searchOrdersPort) {
        this.searchOrdersPort = searchOrdersPort;
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderSearchResponse>> search(@RequestParam("q") String query) {
        return ResponseEntity.ok(searchOrdersPort.search(query));
    }
}
