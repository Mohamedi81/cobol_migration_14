package com.example.customer.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "customer_number", unique = true, nullable = false)
  private String customerNumber;

  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String gender;

  private String addressLine1;
  private String addressLine2;
  private String city;
  private String postalCode;
  private String country;

  private String email;
  private String phone;

  @Enumerated(EnumType.STRING)
  private CustomerStatus status;

  // getters and setters
}
