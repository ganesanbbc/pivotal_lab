package io.pivotal.workshop.RabbitMqListener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MQConsumer {

    @Value("${queue_name}")
    private String routingKey;

    @RabbitListener(queues = "spring-mq-sample")
    public void process(String message) {
        System.out.println(">>> " + message);
    }
}
