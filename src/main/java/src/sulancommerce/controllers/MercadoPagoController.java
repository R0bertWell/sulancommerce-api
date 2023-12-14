package src.sulancommerce.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import src.sulancommerce.models.dtos.MercadoPagoDTO;

@RestController
public class MercadoPagoController {

    @PostMapping("/process_payment")
    public void processPayment(@RequestBody MercadoPagoDTO paymentReq){
        
    }
}
