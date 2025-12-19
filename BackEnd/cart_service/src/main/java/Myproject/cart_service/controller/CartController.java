    package Myproject.cart_service.controller;



    import Myproject.cart_service.dto.reponse.CartItemResponse;
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
        CartService cartService;

        @PostMapping("/createdCartByUserId/{userId}") //   1-> thêm giỏ hàng , bắt đầu tạo giỏ hàng cho user
        public Cart createCartByUserId(@PathVariable String userId) {
            return cartService.createdCartByUserId(userId);
        }


        @PostMapping("/{cartId}/cartItems") //   1 -> sau đó mới thêm cartItem (productId ) vào trong CartId  // tiếp theo đó mới lấy thông tin sản phẩm
        public CartItem addCartItemByCartId( @RequestBody  CartItemCreationRequest request, @PathVariable int cartId) {
            return  cartService.addCartItemByCartId(request,cartId);
        }

        @GetMapping("/getCartByUserId/{userId}") //   1-> lấy cart bằng userId
        public Cart getCartByUserId(@PathVariable String userId) {
            return cartService.getCartByUserId(userId);
        }

        @GetMapping("/{cartId}/cartItems")  //   1-> lấy danh sách cartItem bằng cartId
        public List<CartItemResponse> getCartItemByCartId(@PathVariable int cartId) {
            return  cartService.getCartItemByCartId(cartId);
        }

        @PutMapping("/cartItems/{cartItemId}") //  1-> sửa quatity của cartItem theo cartItemId
        public CartItem updateCartItem(@RequestBody CartItemUpdateRequest request ,  @PathVariable int cartItemId) {
            return cartService.updateCartItemQuantity(request,cartItemId);
        }

        @DeleteMapping("/cartItems/{cartItemId}")   //   1-> xóa cartItems theo cartItemId
        public String deleteCartItem(@PathVariable int cartItemId) {
            return cartService.deleteCartItemByCartId(cartItemId);
        }
    }
