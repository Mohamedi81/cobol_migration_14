package com.example.reporting.controller;

import com.example.reporting.dto.PolicyStatisticsResponse;
import com.example.reporting.service.ReportingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {
  private final ReportingService service;

  public ReportingController(ReportingService service) {
    this.service = service;
  }

  @GetMapping("/policy-statistics")
  public PolicyStatisticsResponse getStatistics() {
    return service.getStatistics();
  }
}
