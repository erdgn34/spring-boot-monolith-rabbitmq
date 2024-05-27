package com.techcareer.rabbitmqornek.tr.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.techcareer.rabbitmqornek.tr.model.Notification;

@Service
public class NotificationListener {

	@RabbitListener(queues = "techcareer-queue")
	public void handleMessage(Notification notification)
	{
		System.out.println("Mesaj Alındı");
		System.out.println(notification.toString());
	}
	
}
