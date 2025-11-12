package com.example.order_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String orderId;

    private String shopAddress;

    private String customerName;
    private String deliveryAddress;
    private String customerPhoneNumber;
    private String note;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String userId; // để lấy danh sách sản phẩm trong giỏ hàng theo id

}
