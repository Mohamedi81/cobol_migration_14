package com.example.policy.controller;

import com.example.policy.dto.PolicyCreateRequest;
import com.example.policy.dto.PolicyResponse;
import com.example.policy.dto.PolicyUpdateRequest;
import com.example.policy.service.PolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {
  private final PolicyService service;

  public PolicyController(PolicyService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<PolicyResponse> create(@RequestBody PolicyCreateRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
  }

  @GetMapping("/{policyNumber}")
  public ResponseEntity<PolicyResponse> get(@PathVariable String policyNumber) {
    return ResponseEntity.ok(service.get(policyNumber));
  }

  @GetMapping
  public ResponseEntity<List<PolicyResponse>> findByCustomer(@RequestParam String customerNumber) {
    return ResponseEntity.ok(service.findByCustomer(customerNumber));
  }

  @PutMapping("/{policyNumber}")
  public ResponseEntity<PolicyResponse> update(@PathVariable String policyNumber, @RequestBody PolicyUpdateRequest request) {
    return ResponseEntity.ok(service.update(policyNumber, request));
  }
}
