package Myproject.cart_service.service;


import Myproject.cart_service.dto.request.CartItemCreationRequest;
import Myproject.cart_service.dto.request.CartItemUpdateRequest;
import Myproject.cart_service.entity.Cart;

import Myproject.cart_service.entity.CartItem;
import Myproject.cart_service.repository.CartItemRepository;
import Myproject.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service

public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart createdCartByUserId( String userId) {
        return cartRepository.getCartsByUserId(userId)
                .orElseGet(() -> {  // Nếu giá trị tồn tại → trả về giá trị đó
                    // 2. Nếu chưa tồn tại → tạo mới
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart);
                });
    }

//    thêm cartItem by cartId , thì lúc lấy ra sẽ lấy cartItem có cartId của user đấy
    public CartItem addCartItemByCartId(CartItemCreationRequest request,  int cartId) {
        CartItem cartItem = cartItemRepository.getByCartIdAndProductId(cartId,request.getProductId())
                .orElseGet(()->{
                    CartItem  newCartItem = new CartItem();
                    newCartItem.setCartId(cartId);
                    newCartItem.setProductId(request.getProductId());
                    newCartItem.setQuantity(request.getQuantity());
                    return newCartItem;
                });


        if(cartItem.getCartId() != 0){
            cartItem.setQuantity(request.getQuantity() + cartItem.getQuantity());
        }
        return cartItemRepository.save(cartItem);
    }

//    lấy giỏ hàng theo userId vì (mỗi người sẽ chỉ có 1 giỏ hàng)
    public Cart getCartByUserId(String userId){
        return cartRepository.getCartsByUserId(userId)
                .orElseThrow(()-> new RuntimeException("không có giỏ hàng "));
    }

//    lấy danh sách cartItem by cartId
    public List<CartItem> getCartItemByCartId(int cartId){
        return cartItemRepository.getCartItemByCartId(cartId);
    }

//    sửa thông tin số lượng(quantity của cartItem)
    public CartItem updateCartItemQuantity(CartItemUpdateRequest request, int cartItemId){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(()->new RuntimeException(" không tìm thấy cartItem"));
        cartItem.setQuantity(request.getQuantity());
        return cartItemRepository.save(cartItem);
    }

//    xóa cartItem bằng cartItemId
    public void  deleteCartItemByCartId(int cartId){
        cartItemRepository.deleteById(cartId);
    }
}
