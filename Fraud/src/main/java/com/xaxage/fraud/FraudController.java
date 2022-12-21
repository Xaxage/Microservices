package com.xaxage.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudController(FraudCheckService fraudCheckService) {

    @GetMapping("{customerId}")
    public ResponseEntity<FraudCheckResponse> isFraudster(@PathVariable Integer customerId) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);

        log.info("Fraud check request for customer {}",customerId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new FraudCheckResponse(isFraudulentCustomer));
    }

}
