package bates.alex.module4topsecret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class userRestController {

    @Autowired
    public InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the CIA! Your identity is safe with us, for now";
    }

    @GetMapping("/checkuser/{username}")
    public String checkUserExists(@PathVariable String username) {
        if (inMemoryUserDetailsManager.userExists(username)) {
            return "User exists";
        } else {
            return "User does not exist";
        }
    }

    @GetMapping("/create/{username}/{password}/{role}")
    public String createUser(@PathVariable String username,
                             @PathVariable String password,
                             @PathVariable String role) {
        inMemoryUserDetailsManager.createUser(User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles(role)
                .build());

        return checkUserExists(username);
    }

    @GetMapping("update/{username}/{password}/{role}")
    public String updateUser(@PathVariable String username,
                             @PathVariable String password,
                             @PathVariable String role) {
        inMemoryUserDetailsManager.updateUser(User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles(role)
                .build());

        return checkUserExists(username);
    }

    @GetMapping("delete/{username}")
    public String deleteUser(@PathVariable String username) {
        inMemoryUserDetailsManager.deleteUser(username);
        return checkUserExists(username);
    }
}
