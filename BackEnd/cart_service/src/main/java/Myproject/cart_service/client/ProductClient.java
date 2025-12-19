package Myproject.cart_service.client;


import Myproject.cart_service.dto.reponse.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products/getProductById/{productId}")
    ProductResponse getProductById(@PathVariable("productId") int productId);
}
