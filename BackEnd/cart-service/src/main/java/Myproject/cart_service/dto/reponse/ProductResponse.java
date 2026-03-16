package Myproject.cart_service.dto.reponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private int productId;
    private String productImageUrl;
    private String productName;
    private double productPrice;
}
