package io.pivotal.workshop.RabbitMqListener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MQConsumer {

    @RabbitListener(queues = "spring-boot")
    public void process(String message) {
        System.out.println(">>> " + message);
    }
}
