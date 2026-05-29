package com.cloud.imc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/") // Associe l'adresse racine (http://localhost:8080/) à cette méthode
    public String helloWorld() {
        return "<h1>Hello World ! Le serveur Cloud IMC fonctionne parfaitement.</h1>";
    }
}