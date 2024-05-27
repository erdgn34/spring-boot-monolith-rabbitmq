package com.techcareer.rabbitmqornek.tr.handler;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techcareer.rabbitmqornek.tr.model.Notification;
import com.techcareer.rabbitmqornek.tr.producer.NotificationProducer;

import jakarta.annotation.PostConstruct;

@Component
public class NotificationSender {

	
	@Autowired
	NotificationProducer producer;
	
	public void startMessage() {
		Thread thread =new Thread(()->{
			while(true)
			{
				Notification notification= new Notification();
				notification.setNotificationId(UUID.randomUUID().toString());
				notification.setCreateAt(new Date());
				notification.setMessage("Mustafa' dan mesaj var");
				notification.setSeen(false);
				producer.sendToQueue(notification);
				try {
					Thread.sleep(5000);
					
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		thread.setName("Notification sender");
		thread.start();
	}
	
	@PostConstruct
	public void init() {
		startMessage();
	}
	
}
