package io.pivotal.workshop;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

//@EnableResourceServer
@EnableScheduling
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RabbitListener(queues = "spring-boot")
    public void process(String message) {
        System.out.println(">>> " + message);
    }

    @Bean
    public Queue queue(){
        return new Queue("spring-boot", false);
    }


    @Autowired
    private  RabbitTemplate template;

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String message = "Hello world! " + timestamp;

        this.template.convertAndSend("spring-boot", message);
    }


}