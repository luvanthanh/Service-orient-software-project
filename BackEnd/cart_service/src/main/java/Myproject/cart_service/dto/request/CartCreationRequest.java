package Myproject.cart_service.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartCreationRequest {
    private String cartName;
    private int cartQuantity;
    private double cartMoney;

    private int productId;
    private String productName;
    private String productImage;
    private double productPrice;

    private String userId;
}
