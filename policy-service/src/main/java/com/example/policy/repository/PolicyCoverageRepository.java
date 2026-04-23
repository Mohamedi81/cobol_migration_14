package com.example.policy.repository;

import com.example.policy.domain.PolicyCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyCoverageRepository extends JpaRepository<PolicyCoverage, Long> {
}
