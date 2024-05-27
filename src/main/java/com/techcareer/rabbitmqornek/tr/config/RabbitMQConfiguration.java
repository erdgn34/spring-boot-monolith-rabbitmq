package com.techcareer.rabbitmqornek.tr.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	@Value("${tr.rabbit.queue.name}")
	private String queueName;
	
	
	@Value("${tr.rabbit.exchange.name}")
	private String exchangeName;
	
	
	@Value("${tr.rabbit.routing.name}")
	private String routingName;
	
	

	@Bean
	public Queue queue() {
		return new Queue(queueName,true);
	}
	
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(exchangeName);
	}
	
	@Bean
	public Binding binding(final Queue queue,final DirectExchange directExchange)
	{
		return BindingBuilder.bind(queue).to(directExchange).with(routingName);
		
	}
	  @Bean
	    public MessageConverter messageConverter() {
	        return new Jackson2JsonMessageConverter();
	    }
	

	public String getQueueName() {
		return queueName;
	}


	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}


	public String getExchangeName() {
		return exchangeName;
	}


	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}


	public String getRoutingName() {
		return routingName;
	}


	public void setRoutingName(String routingName) {
		this.routingName = routingName;
	}
	
	
	
	
}
