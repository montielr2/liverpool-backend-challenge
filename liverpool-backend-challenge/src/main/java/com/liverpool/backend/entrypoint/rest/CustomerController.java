package com.liverpool.backend.entrypoint.rest;

import com.liverpool.backend.application.dto.CreateCustomerRequest;
import com.liverpool.backend.application.dto.CustomerResponse;
import com.liverpool.backend.application.dto.UpdateCustomerRequest;
import com.liverpool.backend.application.usecase.CreateCustomerUseCase;
import com.liverpool.backend.application.usecase.GetCustomerUseCase;
import com.liverpool.backend.application.usecase.SyncCustomerOrdersUseCase;
import com.liverpool.backend.application.usecase.UpdateCustomerUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final SyncCustomerOrdersUseCase syncCustomerOrdersUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase, GetCustomerUseCase getCustomerUseCase, UpdateCustomerUseCase updateCustomerUseCase, SyncCustomerOrdersUseCase syncCustomerOrdersUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.syncCustomerOrdersUseCase = syncCustomerOrdersUseCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CreateCustomerRequest request) {
        return ResponseEntity.status(201).body(createCustomerUseCase.execute(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CustomerResponse> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(getCustomerUseCase.execute(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<CustomerResponse> update(@PathVariable String userId, @Valid @RequestBody UpdateCustomerRequest request) {
        return ResponseEntity.ok(updateCustomerUseCase.execute(userId, request));
    }

    @PostMapping("/{userId}/orders/sync")
    public ResponseEntity<CustomerResponse> syncOrders(@PathVariable String userId) {
        return ResponseEntity.ok(syncCustomerOrdersUseCase.execute(userId));
    }
}
