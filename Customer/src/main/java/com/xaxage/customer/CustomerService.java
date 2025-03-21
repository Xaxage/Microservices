package com.xaxage.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate) {
    public void registerCustomer(CreateCustomerForm createCustomerForm) {

        Customer customer = Customer.builder()
                .firstName(createCustomerForm.firstName())
                .lastName(createCustomerForm.lastName())
                .email(createCustomerForm.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        customerRepository.save(customer);
    }

}
