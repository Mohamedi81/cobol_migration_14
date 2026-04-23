package com.example.reporting.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PolicyStatisticsResponse {
  public LocalDate asOfDate;
  public String groupBy;
  public List<Item> items;

  public static class Item {
    public String productCode;
    public String status;
    public long policyCount;
    public BigDecimal totalPremium;
    public BigDecimal totalSumInsured;
  }
}
