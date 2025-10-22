package Myproject.cart_service.controller;



import Myproject.cart_service.dto.request.CartCreationRequest;
import Myproject.cart_service.dto.request.CartUpdateRequest;
import Myproject.cart_service.entity.Cart;
import Myproject.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

//    lấy all carts
    @GetMapping
    List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable("cartId") int cartId){
        return cartService.getCartById(cartId);
    }
// thêm carts
    @PostMapping
    Cart addCart(@RequestBody CartCreationRequest request){
        return cartService.addCart(request);
    }


//    sủa thông tin cart theo id
    @PutMapping("/{cartId}")
    Cart updateCart(@PathVariable int  cartId, @RequestBody CartUpdateRequest request){
        return cartService.updateCart(cartId,request);
    }

    @DeleteMapping("/{cartId}")
    String deleteCart(@PathVariable int  cartId){
        return cartService.deleteCart(cartId);
    }

}
