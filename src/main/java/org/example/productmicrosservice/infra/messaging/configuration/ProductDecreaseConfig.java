package org.example.productmicrosservice.infra.messaging.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDecreaseConfig {
    @Bean
    public Queue createProductDecreased() {
        return new Queue("products-decreased.queue", false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("products-decreased.ex");
    }

    @Bean
    public Binding createBindingProductDecreased() {
        return BindingBuilder.bind(createProductDecreased()).to(fanoutExchange());
    }
}
