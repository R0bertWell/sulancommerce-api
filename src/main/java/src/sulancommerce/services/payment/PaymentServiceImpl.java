package src.sulancommerce.services.payment;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import src.sulancommerce.models.dtos.payment.PaymentRequest;
import src.sulancommerce.models.dtos.payment.PaymentResponse;
import src.sulancommerce.utils.JsonUtils;
import src.sulancommerce.utils.Utils;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService{
    @Value("${mercadopago.api.url}")
    private String mercadoPagoApiUrl;

    @Value("${mercadopago.api.key}")
    private String mercadoPagoApiKey;

    @Value("${mercadopago.api.token}")
    private String mercadoPagoApiToken;



    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        //test
        //https://www.mercadopago.com.br/developers/panel/app/7435908186813530/credentials/sandbox
        //prod
        //https://www.mercadopago.com.br/developers/panel/app/7435908186813530/credentials/production
        headers.set("Authorization", "Bearer " + mercadoPagoApiToken);
        //https://www.mercadopago.com.br/developers/pt/reference/payments/_payments/post
        headers.set("X-Idempotency-Key", mercadoPagoApiKey);

        paymentRequest.setDate_of_expiration(Utils.getExpirationTime(30));

        HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(paymentRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                mercadoPagoApiUrl + "/payments",
                HttpMethod.POST,
                requestEntity,
                String.class
        );


        return JsonUtils.jsonToObject(responseEntity.getBody(), PaymentResponse.class);
    }

}
