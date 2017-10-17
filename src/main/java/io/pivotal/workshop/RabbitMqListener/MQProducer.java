package io.pivotal.workshop.RabbitMqListener;

import io.pivotal.workshop.sinnpet.model.SnippetInfo;
import io.pivotal.workshop.sinnpet.model.SnippetRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class MQProducer {

    @Autowired
    private RabbitTemplate template;

    @Value("${queue_name}")
    private String routingKey;

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String message = "Hello world! " + timestamp;
        this.template.convertAndSend(new SnippetRecord("Java::"+message,"java1.8"));
//        this.template.convertAndSend(routingKey, message);
    }



}
