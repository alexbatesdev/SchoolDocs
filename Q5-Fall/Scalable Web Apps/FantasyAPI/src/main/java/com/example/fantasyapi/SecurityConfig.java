package com.example.fantasyapi;

import com.example.fantasyapi.Repos.MongoUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MongoUserRepo userRepo;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder().encode("pass")).authorities("USER");
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //JSON
                .antMatchers(HttpMethod.GET, "/devil", "/person", "/location").permitAll()
                .antMatchers(HttpMethod.GET, "/devil/*", "/person/*", "/location/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/devil/*", "/person/*", "/location/*").authenticated()
                .antMatchers(HttpMethod.POST, "/devil", "/person", "/location").authenticated()
                .antMatchers(HttpMethod.PUT, "/devil", "/person", "/location").authenticated()
                //SQL
                .antMatchers(HttpMethod.GET, "/SQL/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/SQL/**").authenticated()
                .antMatchers(HttpMethod.POST, "/SQL/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/SQL/**").authenticated()
                //MONGO
                .antMatchers(HttpMethod.GET, "/MONGO/login").permitAll()
                .antMatchers(HttpMethod.POST, "/MONGO/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/MONGO/*").permitAll()
                .antMatchers(HttpMethod.POST, "/MONGO/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/MONGO/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/MONGO/**").hasAnyRole("ADMIN", "USER")
            //Start of optional bit
                .and().csrf().disable() //disables Cross Site Request Forgery
                .sessionManagement().disable() //disables session management
                //stops the project from caching the user session, stopping the user from staying logged in
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //End of optional bit
                .and()
                .httpBasic()
                .authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint())
                .and().cors();
    }

    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        List<com.example.fantasyapi.Models.User> userList = userRepo.findAll();

        for (com.example.fantasyapi.Models.User user : userList) {
            if (user.getAdmin() != null) {
                if (user.getAdmin()) {
                    userDetailsList.add(User.withUsername(user.getUsername()).password(passwordEncoder().encode(user.getPassword())).roles("ADMIN").build());
                } else {
                    userDetailsList.add(User.withUsername(user.getUsername()).password(passwordEncoder().encode(user.getPassword())).roles("USER").build());
                }
            }
        }

        userDetailsList.add(User.withUsername("admin")
                .password(passwordEncoder().encode("pass")).roles("ADMIN").build());
        userDetailsList.add(User.withUsername("user")
                .password(passwordEncoder().encode("pass")).roles("USER").build());
        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public class NoPopupBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }
}
