package com.patika.advertservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/adverts")
public class AdvertController {

    private final AdvertService advertService;

    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @PostMapping
    public ResponseEntity<AdvertResponse> createAdvert(@RequestBody AdvertRequest advertRequest){
        return ResponseEntity.ok(advertService.createAdvert(advertRequest));
    }

}
