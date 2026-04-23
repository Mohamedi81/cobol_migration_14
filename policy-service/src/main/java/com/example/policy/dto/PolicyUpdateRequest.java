package com.example.policy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PolicyUpdateRequest {
  public LocalDate effectiveDate;
  public Integer termMonths;
  public String status;
  public List<CoverageDto> coverage;

  public static class CoverageDto {
    public String coverageCode;
    public BigDecimal sumInsured;
  }
}
