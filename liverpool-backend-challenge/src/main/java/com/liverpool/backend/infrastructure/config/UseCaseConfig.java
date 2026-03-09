package com.liverpool.backend.infrastructure.config;

import com.liverpool.backend.application.usecase.CreateCustomerUseCase;
import com.liverpool.backend.application.usecase.GetCustomerUseCase;
import com.liverpool.backend.application.usecase.SyncCustomerOrdersUseCase;
import com.liverpool.backend.application.usecase.UpdateCustomerUseCase;
import com.liverpool.backend.domain.port.CustomerRepositoryPort;
import com.liverpool.backend.domain.port.OrdersProviderPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        return new CreateCustomerUseCase(customerRepositoryPort);
    }

    @Bean
    public GetCustomerUseCase getCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        return new GetCustomerUseCase(customerRepositoryPort);
    }

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        return new UpdateCustomerUseCase(customerRepositoryPort);
    }

    @Bean
    public SyncCustomerOrdersUseCase syncCustomerOrdersUseCase(CustomerRepositoryPort customerRepositoryPort, OrdersProviderPort ordersProviderPort) {
        return new SyncCustomerOrdersUseCase(customerRepositoryPort, ordersProviderPort);
    }
}
