package com.example.order_service.dto.request;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateRequest {
    private String shopAddress;

    private String customerName;
    private String deliveryAddress;
    private String customerPhoneNumber;
    private String note;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String userId;
}
