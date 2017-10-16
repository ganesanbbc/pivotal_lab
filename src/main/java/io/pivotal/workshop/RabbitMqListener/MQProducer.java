package io.pivotal.workshop.RabbitMqListener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class MQProducer {

    @Autowired
    private RabbitTemplate template;

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String message = "Hello world! " + timestamp;

        this.template.convertAndSend("spring-boot", message);
    }



}
