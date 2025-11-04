package Myproject.cart_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private String cartName;
    private int cartQuantity;
    private double cartMoney;

    private int productId;
    private String productName;
    private String productImage;
    private double productPrice;

    private String userId;
}
