package com.patika.advertservice;

import com.patika.advertservice.messaging.MessagingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final MessagingService messagingService;

    public AdvertService(AdvertRepository advertRepository, MessagingService messagingService) {
        this.advertRepository = advertRepository;
        this.messagingService = messagingService;
    }


    public AdvertResponse createAdvert(AdvertRequest advertRequest) {

        Advert createdAdvert = Advert.builder()
                .title(advertRequest.getTitle())
                .message(advertRequest.getMessage())
                .price(advertRequest.getPrice())
                .createdAt(LocalDateTime.now())
                .userId(advertRequest.getUserId())
                .build();

        advertRepository.saveAndFlush(createdAdvert);

        messagingService.sendMessage(createdAdvert);

        return AdvertResponse.builder()
                .id(createdAdvert.getId())
                .userId(createdAdvert.getUserId())
                .build();
    }
}
