package com.example.basicauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("pass")).authorities("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/agent/secret").authenticated() //sets up the security for the /agent/secret endpoint to only allow USER
                .antMatchers("/agent/notsecret").permitAll() //sets up the security for the /agent/notsecret endpoint to allow all
                .antMatchers("/index.html").permitAll() //sets up the security for the /index.html endpoint to allow all
                  //Start of optional bit
                    .and().csrf().disable() //disables Cross Site Request Forgery
                    .sessionManagement().disable() //disables session management
                    //stops the project from chaching the user session, stops the user from staying logged in
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  //End of optional bit
                .and()
                .httpBasic()
                .authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint());

        //.antMatchers("/path/*") //single path with 1 subfolder ex: /path/{id}
        //.antMatchers("/path/*", RequestMethod.GET) //specify protocol
        //.antMatchers("/path/**") //Recursively applies to all subfolders ex: /path/{id}/{id2}
        //.antMatchers(HttpMethod.GET, "/api/ships/*", "/api/planets/*").permitAll() //specify protocol and multiple paths
        //.antMatchers(HttpMethod.GET, "/api/login").authenticated() //specify just one


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
