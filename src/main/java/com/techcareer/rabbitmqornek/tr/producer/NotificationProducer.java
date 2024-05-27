package com.techcareer.rabbitmqornek.tr.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.techcareer.rabbitmqornek.tr.config.RabbitMQConfiguration;
import com.techcareer.rabbitmqornek.tr.model.Notification;

@Service
public class NotificationProducer {

	@Value("${tr.rabbit.exchange.name}")
	private String exchangeName;
	
	
	@Value("${tr.rabbit.routing.name}")
	private String routingName; 
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private RabbitMQConfiguration rabbitMQConfiguration;
	
	public void sendToQueue(Notification notification)
	{
		System.out.println("Notification Sent ID : "+notification.getNotificationId());
		rabbitTemplate.setMessageConverter(rabbitMQConfiguration.messageConverter());
		rabbitTemplate.convertAndSend(exchangeName, routingName, notification);
	}
	
	
}
