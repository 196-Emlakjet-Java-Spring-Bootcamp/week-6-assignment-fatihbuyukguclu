package com.patika.advertservice;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AdvertResponse {

    private Long id;
    private Long userId;

}
