package com.example.policy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PolicyResponse {
  public String policyNumber;
  public String customerNumber;
  public String productCode;
  public LocalDate startDate;
  public Integer termMonths;
  public BigDecimal premium;
  public String currency;
  public String status;
  public List<CoverageDto> coverage;

  public static class CoverageDto {
    public String coverageCode;
    public BigDecimal sumInsured;
  }
}
