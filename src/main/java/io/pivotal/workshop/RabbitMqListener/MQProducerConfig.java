package io.pivotal.workshop.RabbitMqListener;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQProducerConfig {

    private final ConnectionFactory connectionFactory;

    public String getRoutingKey() {
        return routingKey;
    }

    @Value("${queue_name}")
    private String routingKey;

    @Autowired
    public MQProducerConfig(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public RabbitTemplate template() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory);
        rabbitTemplate.setRoutingKey(routingKey);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}