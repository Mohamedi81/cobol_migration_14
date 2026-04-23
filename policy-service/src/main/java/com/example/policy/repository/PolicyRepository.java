package com.example.policy.repository;

import com.example.policy.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
  Optional<Policy> findByPolicyNumber(String policyNumber);
  List<Policy> findByCustomerNumber(String customerNumber);
}
