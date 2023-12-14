package src.sulancommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ServerSideController {

    @GetMapping(value = "/getServerUrl", produces = "text/plain")
    public String getServerUrl(){
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }
}
