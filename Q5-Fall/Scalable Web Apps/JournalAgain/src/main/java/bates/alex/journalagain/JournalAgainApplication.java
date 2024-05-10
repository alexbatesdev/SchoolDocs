package bates.alex.journalagain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class JournalAgainApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalAgainApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*"); //allow from anywhere
				//registry.addMapping("/**").allowedOrigins("http://localhost:8082/").allowedMethods("*"); //allow from only ui
			}
		};
	}
}
