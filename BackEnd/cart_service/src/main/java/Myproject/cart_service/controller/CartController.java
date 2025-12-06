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


//   1-> thêm giỏ hàng , bắt đầu tạo giỏ hàng cho user
    @PostMapping("/createdCartByUserId/{userId}")
    public Cart createCartByUserId(@PathVariable String userId) {
        return cartService.createdCartByUserId(userId);
    }

//   1 -> sau đó mới thêm cartItem (productId ) vào trong CartId  // tiếp theo đó mới lấy thông tin sản phẩm
    @PostMapping("/{cartId}/cartItems")
    public CartItem addCartItemByCartId( @RequestBody  CartItemCreationRequest request, @PathVariable int cartId) {
        return  cartService.addCartItemByCartId(request,cartId);
    }
//   1-> lấy cart bằng userId
    @GetMapping("/getCartByUserId/{userId}")
    public Cart getCartByUserId(@PathVariable String userId) {
        return cartService.getCartByUserId(userId);
    }
//   1-> lấy danh sách cartItem bằng cartId
    @GetMapping("/{cartId}/cartItems")
    public List<CartItem> getCartItemByCartId(@PathVariable int cartId) {
        return  cartService.getCartItemByCartId(cartId);
    }
//  1-> sửa quatity của cartItem theo cartItemId
    @PutMapping("/cartItems/{cartItemId}")
    public CartItem updateCartItem(@RequestBody CartItemUpdateRequest request ,  @PathVariable int cartItemId) {
        return cartService.updateCartItemQuantity(request,cartItemId);
    }
//   1-> xóa cartItems theo cartItemId
    @DeleteMapping("/cartItems/{cartItemId}")
    public String deleteCartItem(@PathVariable int cartItemId) {
        return cartService.deleteCartItemByCartId(cartItemId);
    }
}
