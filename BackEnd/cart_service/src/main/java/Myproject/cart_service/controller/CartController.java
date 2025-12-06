package Myproject.cart_service.controller;



import Myproject.cart_service.dto.request.CartItemCreationRequest;
import Myproject.cart_service.dto.request.CartItemUpdateRequest;
import Myproject.cart_service.entity.Cart;
import Myproject.cart_service.entity.CartItem;
import Myproject.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;


//    thêm giỏ hàng , bắt đầu tạo giỏ hàng cho user
    @PostMapping("/createdCartByUserId/{userId}")
    public Cart createCartByUserId(@PathVariable String userId) {
        return cartService.createdCartByUserId(userId);
    }

// sau đó mới thêm cartItem (productId ) vào trong CartId  // tiếp theo đó mới lấy thông tin sản phâmr
    @PostMapping("/addCartItemByCartId/{cartId}")
    public CartItem addCartItemByCartId( @RequestBody  CartItemCreationRequest request, @PathVariable int cartId) {
        return  cartService.addCartItemByCartId(request,cartId);
    }
// lấy cart bằng userId
    @GetMapping("/getCartByUserId/{userId}")
    public Cart getCartByUserId(@PathVariable String userId) {
        return cartService.getCartByUserId(userId);
    }
// lấy danh sách cartItem bằng cartId
    @GetMapping("/getCartItemByCartId/{cartId}")
    public List<CartItem> getCartItemByCartId(@PathVariable int cartId) {
        return  cartService.getCartItemByCartId(cartId);
    }

    @PutMapping("/updateCartItem/{cartItemId}")
    public CartItem updateCartItem(@RequestBody CartItemUpdateRequest request ,  @PathVariable int cartItemId) {
        return cartService.updateCartItemQuantity(request,cartItemId);
    }

    @DeleteMapping("/deleteCartItem/{cartId}")
    public void deleteCartItem(@PathVariable int cartId) {
        cartService.deleteCartItemByCartId(cartId);
    }

}
