package online.bnkwebdev.apirecepcao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Liga o motor de tarefas automáticas
public class ApiRecepcaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRecepcaoApplication.class, args);
    }

}
