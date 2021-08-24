package dev.brunoliveira.hrpayroll.service;

import dev.brunoliveira.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment getPayment(Long workerId, int days) {
        return new Payment("Anakyn", 200.0, days);
    }
}
