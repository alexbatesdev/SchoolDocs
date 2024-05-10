package com.example.customexceptions;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/throw")
public class ErrorRestController {

    @GetMapping("")
    public String throwError() {
        throw new NotAllowedInUniverseException();
    }

}
