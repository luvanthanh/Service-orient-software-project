package LuVanThanh.Myproject.request.phoneRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneBetweenPrice {
    private long  priceMin;
    private long priceMax;
}
