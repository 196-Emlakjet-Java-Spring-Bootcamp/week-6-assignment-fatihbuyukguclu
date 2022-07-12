package com.patika.notificationservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public NotificationService(NotificationRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = {"advert-queue"})
    public void messageListener(@Payload Advert advert){

        Notification notification = Notification.builder()
                .advertId(advert.getId())
                .date(advert.getCreatedAt())
                .userId(advert.getUserId())
                .state("Sent")
                .build();
        repository.save(notification);
    }



}
