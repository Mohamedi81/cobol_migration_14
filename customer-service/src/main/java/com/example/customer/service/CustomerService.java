package com.example.customer.service;

import com.example.customer.domain.Customer;
import com.example.customer.domain.CustomerStatus;
import com.example.customer.dto.CustomerRequest;
import com.example.customer.dto.CustomerResponse;
import com.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
  private final CustomerRepository repository;

  public CustomerService(CustomerRepository repository) {
    this.repository = repository;
  }

  public CustomerResponse create(CustomerRequest request) {
    Customer customer = new Customer();
    customer.setCustomerNumber("C" + UUID.randomUUID().toString().replace("-", "").substring(0, 9));
    mapRequestToEntity(request, customer);
    customer.setStatus(CustomerStatus.ACTIVE);
    repository.save(customer);
    return mapEntityToResponse(customer);
  }

  public CustomerResponse update(String customerNumber, CustomerRequest request) {
    Customer customer = repository.findByCustomerNumber(customerNumber)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
    mapRequestToEntity(request, customer);
    repository.save(customer);
    return mapEntityToResponse(customer);
  }

  public CustomerResponse get(String customerNumber) {
    Customer customer = repository.findByCustomerNumber(customerNumber)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
    return mapEntityToResponse(customer);
  }

  private void mapRequestToEntity(CustomerRequest request, Customer customer) {
    customer.setFirstName(request.firstName);
    customer.setLastName(request.lastName);
    customer.setDateOfBirth(request.dateOfBirth);
    customer.setGender(request.gender);
    customer.setAddressLine1(request.addressLine1);
    customer.setAddressLine2(request.addressLine2);
    customer.setCity(request.city);
    customer.setPostalCode(request.postalCode);
    customer.setCountry(request.country);
    customer.setEmail(request.email);
    customer.setPhone(request.phone);
  }

  private CustomerResponse mapEntityToResponse(Customer customer) {
    CustomerResponse response = new CustomerResponse();
    response.customerNumber = customer.getCustomerNumber();
    response.firstName = customer.getFirstName();
    response.lastName = customer.getLastName();
    response.dateOfBirth = customer.getDateOfBirth();
    response.gender = customer.getGender();
    response.addressLine1 = customer.getAddressLine1();
    response.addressLine2 = customer.getAddressLine2();
    response.city = customer.getCity();
    response.postalCode = customer.getPostalCode();
    response.country = customer.getCountry();
    response.email = customer.getEmail();
    response.phone = customer.getPhone();
    response.status = customer.getStatus().name();
    return response;
  }
}
