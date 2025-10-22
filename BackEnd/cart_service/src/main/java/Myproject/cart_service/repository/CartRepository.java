package Myproject.cart_service.repository;

import Myproject.cart_service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,String> {
    Cart getByCartId(int cartId);
}
