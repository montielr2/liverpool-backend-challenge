package com.liverpool.backend.entrypoint.rest;

import com.liverpool.backend.application.dto.CreateCustomerRequest;
import com.liverpool.backend.application.dto.CustomerResponse;
import com.liverpool.backend.application.dto.UpdateCustomerRequest;
import com.liverpool.backend.application.usecase.CreateCustomerUseCase;
import com.liverpool.backend.application.usecase.GetCustomerUseCase;
import com.liverpool.backend.application.usecase.SyncCustomerOrdersUseCase;
import com.liverpool.backend.application.usecase.UpdateCustomerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * REST controller responsible for customer operations.
 */
@Tag(name = "Customers", description = "Operaciones para crear, consultar y actualizar clientes")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
  private final CreateCustomerUseCase createCustomerUseCase;
  private final GetCustomerUseCase getCustomerUseCase;
  private final UpdateCustomerUseCase updateCustomerUseCase;
  private final SyncCustomerOrdersUseCase syncCustomerOrdersUseCase;

  public CustomerController(
      CreateCustomerUseCase createCustomerUseCase,
      GetCustomerUseCase getCustomerUseCase,
      UpdateCustomerUseCase updateCustomerUseCase,
      SyncCustomerOrdersUseCase syncCustomerOrdersUseCase) {
    this.createCustomerUseCase = createCustomerUseCase;
    this.getCustomerUseCase = getCustomerUseCase;
    this.updateCustomerUseCase = updateCustomerUseCase;
    this.syncCustomerOrdersUseCase = syncCustomerOrdersUseCase;
  }

  @Operation(
      summary = "Crear cliente",
      description = "Crea un nuevo cliente y lo persiste en la base de datos")
  @PostMapping
  public ResponseEntity<CustomerResponse> create(
      @Valid @RequestBody CreateCustomerRequest request) {
    return ResponseEntity.status(201).body(createCustomerUseCase.execute(request));
  }
    /**
     * Retrieves a customer by id.
     *
     * @param id customer identifier
     * @return customer information
     */
  @Operation(
      summary = "Consultar cliente por userId",
      description = "Obtiene la información de un cliente a partir de su identificador de usuario")
  @GetMapping("/{userId}")
  public ResponseEntity<CustomerResponse> getByUserId(@PathVariable String userId) {
    return ResponseEntity.ok(getCustomerUseCase.execute(userId));
  }

  @Operation(
      summary = "Actualizar cliente",
      description = "Actualiza los datos de un cliente existente")
  @PutMapping("/{userId}")
  public ResponseEntity<CustomerResponse> update(
      @PathVariable String userId, @Valid @RequestBody UpdateCustomerRequest request) {
    return ResponseEntity.ok(updateCustomerUseCase.execute(userId, request));
  }

  @Operation(summary = "Consultar pedidos", description = "Sincromniza los pedidos de un usuario")
  @PostMapping("/{userId}/orders/sync")
  public ResponseEntity<CustomerResponse> syncOrders(@PathVariable String userId) {
    return ResponseEntity.ok(syncCustomerOrdersUseCase.execute(userId));
  }
}
