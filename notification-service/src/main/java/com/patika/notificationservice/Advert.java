package com.patika.notificationservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Advert {

    private Long id;
    private String title;
    private BigDecimal price;
    private String message;
    private LocalDateTime createdAt;
    private Long userId;

}
