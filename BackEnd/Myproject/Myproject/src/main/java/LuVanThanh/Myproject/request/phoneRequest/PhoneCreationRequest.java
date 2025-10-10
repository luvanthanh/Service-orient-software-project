package LuVanThanh.Myproject.request.phoneRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneCreationRequest {
    private int id;
    private String brand;
    private String productName;
    private Double screenSize;
    private String color;
    private int ram;
    private int rom;
    private String description;
    private LocalDate releaseDate;
    private int stockQuantity;
    private int warranty;
    private long price;
    private String formattedPrice;
    private String imageUrl;
}
