package com.example.policy.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "policies")
public class Policy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "policy_number", unique = true, nullable = false)
  private String policyNumber;

  private String customerNumber;
  private String productCode;
  private LocalDate startDate;
  private Integer termMonths;
  private BigDecimal premium;
  private String currency;

  @Enumerated(EnumType.STRING)
  private PolicyStatus status;

  @Version
  private Long version;

  @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PolicyCoverage> coverages;

  // getters and setters
}
