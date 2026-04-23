package com.example.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportingRepository extends CrudRepository<Object, Long> {
  @Query(value = "SELECT product_code, status, COUNT(*) as policy_count, SUM(premium) as total_premium FROM policies GROUP BY product_code, status", nativeQuery = true)
  List<Object[]> getPolicyStatistics();
}
