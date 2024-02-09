package src.sulancommerce.configs.errorHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST)
                .body("Ocorreu um erro: " + e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleAuthException(BadCredentialsException e, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST)
                .body("Falha ao realizar login: " + e.getMessage());
    }
}