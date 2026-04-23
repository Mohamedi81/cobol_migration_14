package com.example.policy.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "policy_coverages")
public class PolicyCoverage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "policy_id")
  private Policy policy;

  private String coverageCode;
  private BigDecimal sumInsured;

  // getters and setters
}
