package src.sulancommerce.services.payment;

import org.json.JSONException;
import src.sulancommerce.models.dtos.payment.PaymentRequest;
import src.sulancommerce.models.dtos.payment.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest paymentRequest) throws JSONException;
}
