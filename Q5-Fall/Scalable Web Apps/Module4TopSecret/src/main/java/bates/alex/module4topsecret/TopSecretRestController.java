package bates.alex.module4topsecret;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cia")
public class TopSecretRestController {

    @GetMapping("/noclist")
    @PreAuthorize("hasAnyRole('NOCLIST', 'DIRECTOR')") //aso hasRole, hasAuthority, hasAnyAuthority
    public String nocList() {
        return "Noclist";
    }

    @GetMapping("/missions")
    @PreAuthorize("hasAnyRole('MISSIONS', 'DIRECTOR')")
    public String nextMission() {
        return "Your next mission is to go to the store and buy some milk";
    }

    @GetMapping("/anonymous")
    public String anonymous() {
        return "Welcome to the CIA! Your identity is safe with us, for now";
    }

    @GetMapping("/login")
    public void login() {

    }
}
