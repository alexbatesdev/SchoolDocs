package com.example.fantasyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
//        (exclude = {
//        MongoAutoConfiguration.class,
//        MongoDataAutoConfiguration.class
//})
public class FantasyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FantasyApiApplication.class, args);

//        DatabaseConnect.insertPerson("Denji", false, "The main character of Chainsaw Man, he is a human-devil hybrid who has the ability to transform into a chainsaw-wielding monster.");
//        DatabaseConnect.insertPerson("Aki Hayakawa", true, "IDK man, he kinda just wants to kill the gun devil and is Denji's big brother figure");
//        DatabaseConnect.insertPerson("Power", true, "Power is the blood fiend, a demon who took over a human corpse. She has high mental abilities and is part of the Hayakawa household.");
//        DatabaseConnect.insertDevil("Pochita", "Chainsaw", "Denji's best friend, the Chainsaw Devil who takes on a dog-like appearance");
//        DatabaseConnect.insertDevil("Gun Devil", "Gun", "The Gun Devil is the devil that killed billions in mere minutes. It has been killed and portions of it are owned by world governments");
//        DatabaseConnect.insertLocation("Tokyo", "The capital of Japan, and the largest metropolitan area in the world.");

//        ArrayList<Person> people = DatabaseConnect.selectAllPeople();
//        if (people != null) {
//            for (Person person : people) {
//                System.out.println(person);
//            }
//        }
//
//        ArrayList<Devil> devils = DatabaseConnect.selectAllDevils();
//        if (devils != null) {
//            for (Devil devil : devils) {
//                System.out.println(devil);
//            }
//        }

        //MONGONION.readDenji();
        //MONGONION.readAki();
        //MONGONION.readPower();
        //MONGONION.readPochita();
        //MONGONION.readGunDevil();


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
