package com.patika.advertservice;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdvertService {

    private final AdvertRepository advertRepository;

    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
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

        return AdvertResponse.builder()
                .id(createdAdvert.getId())
                .userId(createdAdvert.getUserId())
                .build();
    }
}
