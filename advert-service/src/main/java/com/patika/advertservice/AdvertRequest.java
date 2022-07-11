package com.patika.advertservice;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AdvertRequest {

    private String title;
    private BigDecimal price;
    private String message;
    private Long userId;

}
