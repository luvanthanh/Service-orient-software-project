package Myproject.cart_service.repository;

import Myproject.cart_service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,String> {
    Cart getByCartId(int cartId);
    Optional<List<Cart>> getCartsByUserId(String userId);
}
