package com.xaxage.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/V1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public ResponseEntity<HttpStatus> registerCustomer(@RequestBody CreateCustomerForm createCustomerForm) {
        log.info("New customer registration {}", createCustomerForm);

        customerService.registerCustomer(createCustomerForm);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
