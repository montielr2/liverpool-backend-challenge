package com.liverpool.backend.domain.port;

import com.liverpool.backend.application.dto.OrderSearchResponse;
import java.util.List;

public interface SearchOrdersPort {
  List<OrderSearchResponse> search(String query);
}
