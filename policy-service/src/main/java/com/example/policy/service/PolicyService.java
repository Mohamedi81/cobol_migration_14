package com.example.policy.service;

import com.example.policy.client.CustomerClient;
import com.example.policy.domain.Policy;
import com.example.policy.domain.PolicyCoverage;
import com.example.policy.domain.PolicyStatus;
import com.example.policy.dto.PolicyCreateRequest;
import com.example.policy.dto.PolicyResponse;
import com.example.policy.dto.PolicyUpdateRequest;
import com.example.policy.repository.PolicyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PolicyService {
  private final PolicyRepository repository;
  private final CustomerClient customerClient;

  public PolicyService(PolicyRepository repository, CustomerClient customerClient) {
    this.repository = repository;
    this.customerClient = customerClient;
  }

  @Transactional
  public PolicyResponse create(PolicyCreateRequest request) {
    if (!customerClient.customerExists(request.customerNumber)) {
      throw new RuntimeException("Customer does not exist");
    }

    Policy policy = new Policy();
    policy.setPolicyNumber("P" + UUID.randomUUID().toString().replace("-", "").substring(0, 9));
    policy.setCustomerNumber(request.customerNumber);
    policy.setProductCode(request.productCode);
    policy.setStartDate(request.startDate);
    policy.setTermMonths(request.termMonths);
    policy.setPremium(request.premium);
    policy.setCurrency(request.currency);
    policy.setStatus(PolicyStatus.NEW);

    List<PolicyCoverage> coverages = request.coverage.stream().map(c -> {
      PolicyCoverage pc = new PolicyCoverage();
      pc.setPolicy(policy);
      pc.setCoverageCode(c.coverageCode);
      pc.setSumInsured(c.sumInsured);
      return pc;
    }).collect(Collectors.toList());
    policy.setCoverages(coverages);

    repository.save(policy);
    return mapEntityToResponse(policy);
  }

  public PolicyResponse get(String policyNumber) {
    Policy policy = repository.findByPolicyNumber(policyNumber)
        .orElseThrow(() -> new RuntimeException("Policy not found"));
    return mapEntityToResponse(policy);
  }

  public List<PolicyResponse> findByCustomer(String customerNumber) {
    return repository.findByCustomerNumber(customerNumber).stream()
        .map(this::mapEntityToResponse)
        .collect(Collectors.toList());
  }

  @Transactional
  public PolicyResponse update(String policyNumber, PolicyUpdateRequest request) {
    Policy policy = repository.findByPolicyNumber(policyNumber)
        .orElseThrow(() -> new RuntimeException("Policy not found"));

    if (request.termMonths != null) policy.setTermMonths(request.termMonths);
    if (request.status != null) policy.setStatus(PolicyStatus.valueOf(request.status));

    if (request.coverage != null) {
      policy.getCoverages().clear();
      policy.getCoverages().addAll(request.coverage.stream().map(c -> {
        PolicyCoverage pc = new PolicyCoverage();
        pc.setPolicy(policy);
        pc.setCoverageCode(c.coverageCode);
        pc.setSumInsured(c.sumInsured);
        return pc;
      }).collect(Collectors.toList()));
    }

    repository.save(policy);
    return mapEntityToResponse(policy);
  }

  private PolicyResponse mapEntityToResponse(Policy policy) {
    PolicyResponse response = new PolicyResponse();
    response.policyNumber = policy.getPolicyNumber();
    response.customerNumber = policy.getCustomerNumber();
    response.productCode = policy.getProductCode();
    response.startDate = policy.getStartDate();
    response.termMonths = policy.getTermMonths();
    response.premium = policy.getPremium();
    response.currency = policy.getCurrency();
    response.status = policy.getStatus().name();

    response.coverage = policy.getCoverages().stream().map(c -> {
      PolicyResponse.CoverageDto cd = new PolicyResponse.CoverageDto();
      cd.coverageCode = c.getCoverageCode();
      cd.sumInsured = c.getSumInsured();
      return cd;
    }).collect(Collectors.toList());
    return response;
  }
}
