package com.example.customer.controller;

import com.example.customer.dto.CustomerRequest;
import com.example.customer.dto.CustomerResponse;
import com.example.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService service;

  public CustomerController(CustomerService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
  }

  @PutMapping("/{customerNumber}")
  public ResponseEntity<CustomerResponse> update(@PathVariable String customerNumber, @RequestBody CustomerRequest request) {
    return ResponseEntity.ok(service.update(customerNumber, request));
  }

  @GetMapping("/{customerNumber}")
  public ResponseEntity<CustomerResponse> get(@PathVariable String customerNumber) {
    return ResponseEntity.ok(service.get(customerNumber));
  }
}
