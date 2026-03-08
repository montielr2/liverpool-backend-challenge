package com.liverpool.backend.domain.port;

import java.util.List;

public interface OrdersProviderPort {
    List<String> findOrderRefsByUserId(String userId);
}
