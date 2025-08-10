package br.com.middleware.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.middleware")
public class Aplicacao {
    public static void main(String[] args) {
        SpringApplication.run(Aplicacao.class, args);
    }
}
