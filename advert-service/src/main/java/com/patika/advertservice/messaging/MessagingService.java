package com.patika.advertservice.messaging;

import com.patika.advertservice.Advert;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public MessagingService(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void sendMessage(Advert advert){
        rabbitTemplate.convertAndSend(this.queue.getName(), advert);
    }
}
