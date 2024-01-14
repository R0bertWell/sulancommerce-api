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

    private void teste(){
        MercadoPago.SDK.setAccessToken("YOUR_ACCESS_TOKEN");


        Payment payment = new Payment();
        payment.setTransactionAmount(Float.valueOf(request.getParameter("transactionAmount")))
                .setToken(request.getParameter("token"))
                .setDescription(request.getParameter("description"))
                .setInstallments(Integer.valueOf(request.getParameter("installments")))
                .setPaymentMethodId(request.getParameter("paymentMethodId"))
                .setNotificationUrl("http://requestbin.fullcontact.com/1ogudgk1");


        Identification identification = new Identification();
        identification.setType(request.getParameter("docType"))
                .setNumber(request.getParameter("docNumber"));


        Payer payer = new Payer();
        payer.setEmail(request.getParameter("email"))
                .setIdentification(identification);

        payment.setPayer(payer);


        payment.save();


        System.out.println(payment.getStatus());

    }
}
