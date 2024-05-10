package bates.alex.module4topsecret;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("ethan")
//                .password(passwordEncoder().encode("hunt")).roles("MISSIONS");
//        auth.inMemoryAuthentication().withUser("william")
//                .password(passwordEncoder().encode("donloe")).roles("NOCLIST");
//        auth.inMemoryAuthentication().withUser("kittridge")
//                .password(passwordEncoder().encode("1234")).roles("DIRECTOR");

        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/cia/anonymous").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/script.js").permitAll()
                .antMatchers("/cia/login").permitAll()
                .antMatchers("/user/**").hasRole("DIRECTOR")
                //.antMatchers("/cia/missions").hasAnyRole("MISSIONS", "DIRECTOR")
                //.antMatchers("/cia/anonymous").hasAnyRole("NOCLIST", "DIRECTOR")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .httpBasic()
                .authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint())
                .and().cors();
    }

    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User.withUsername("ethan")
                .password(passwordEncoder().encode("hunt")).roles("MISSIONS").build());
        userDetailsList.add(User.withUsername("william")
                .password(passwordEncoder().encode("donloe")).roles("NOCLIST").build());
        userDetailsList.add(User.withUsername("kittridge")
                .password(passwordEncoder().encode("1234")).roles("DIRECTOR").build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    public class NoPopupBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }
}
