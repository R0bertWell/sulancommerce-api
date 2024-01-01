package src.sulancommerce.controllers;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import src.sulancommerce.models.dtos.payment.PaymentRequest;
import src.sulancommerce.models.dtos.payment.PaymentResponse;
import src.sulancommerce.services.payment.PaymentService;

@RestController
public class MercadoPagoController {

    private final PaymentService paymentService;

    public MercadoPagoController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/v1/process_payment")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest) throws JSONException {
        PaymentResponse json = paymentService.processPayment(paymentRequest);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
