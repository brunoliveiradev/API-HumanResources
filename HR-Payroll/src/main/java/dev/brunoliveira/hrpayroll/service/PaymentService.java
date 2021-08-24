package dev.brunoliveira.hrpayroll.service;

import dev.brunoliveira.hrpayroll.entities.Payment;
import dev.brunoliveira.hrpayroll.entities.Worker;
import dev.brunoliveira.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }

    public Payment getPayment(Long workerId, int days) {
        // Como é do tipo ResponseEntity, precisa colocar .getBody() para capturar a resposta
        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
