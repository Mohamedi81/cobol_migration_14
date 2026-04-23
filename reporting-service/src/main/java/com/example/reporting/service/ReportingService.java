package com.example.reporting.service;

import com.example.reporting.dto.PolicyStatisticsResponse;
import com.example.reporting.repository.ReportingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportingService {
  private final ReportingRepository repository;

  public ReportingService(ReportingRepository repository) {
    this.repository = repository;
  }

  public PolicyStatisticsResponse getStatistics() {
    List<Object[]> rows = repository.getPolicyStatistics();
    PolicyStatisticsResponse response = new PolicyStatisticsResponse();
    response.asOfDate = LocalDate.now();
    response.groupBy = "PRODUCT_STATUS";
    response.items = rows.stream().map(r -> {
      PolicyStatisticsResponse.Item item = new PolicyStatisticsResponse.Item();
      item.productCode = (String) r[0];
      item.status = (String) r[1];
      item.policyCount = ((Number) r[2]).longValue();
      item.totalPremium = (BigDecimal) r[3];
      item.totalSumInsured = BigDecimal.ZERO;
      return item;
    }).collect(Collectors.toList());
    return response;
  }
}
