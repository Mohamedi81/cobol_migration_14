package com.example.policy.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CustomerClient {
  private final WebClient webClient;

  public CustomerClient(@Value("${customer-service.base-url}") String baseUrl) {
    this.webClient = WebClient.builder().baseUrl(baseUrl).build();
  }

  public boolean customerExists(String customerNumber) {
    try {
      webClient.get().uri("/{customerNumber}", customerNumber)
          .retrieve()
          .bodyToMono(String.class)
          .block();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
