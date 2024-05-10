package com.example.basicauth;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
public class TopSecretRestController {

    @GetMapping("/secret")
    public String topSecret() {
        return "This is top secret!";
    }

    @GetMapping("/notsecret")
    public String notSoSecret() {
        return "This is not so secret!";
    }

    @GetMapping("/logincheck")
    public void loginCheck() {

    }

}
