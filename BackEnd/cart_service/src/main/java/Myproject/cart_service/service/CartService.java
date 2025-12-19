package Myproject.cart_service.service;

import Myproject.cart_service.client.ProductClient;
import Myproject.cart_service.dto.reponse.CartItemResponse;
import Myproject.cart_service.dto.reponse.ProductResponse;
import Myproject.cart_service.dto.request.CartItemCreationRequest;
import Myproject.cart_service.dto.request.CartItemUpdateRequest;
import Myproject.cart_service.entity.Cart;
import Myproject.cart_service.entity.CartItem;
import Myproject.cart_service.repository.CartItemRepository;
import Myproject.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;


    public Cart createdCartByUserId(String userId){
        Cart  cart = cartRepository.getCartsByUserId( userId)
                .orElseGet(()->{
                    Cart newCart =new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart);
                });
        return cart;
    }

    public CartItem addCartItemByCartId(CartItemCreationRequest request, @PathVariable int cartId) {
        CartItem cartItem = cartItemRepository.getByCartIdAndProductId(cartId, request.getProductId())
                .orElseGet(()->{
                    CartItem newCartItem = new CartItem();
                    newCartItem.setCartId(cartId);
                    newCartItem.setProductId(request.getProductId());
                    newCartItem.setQuantity(request.getQuantity());
                    return cartItemRepository.save(newCartItem);
                });
        if(cartItem!=null){
            cartItem.setQuantity(request.getQuantity()+cartItem.getQuantity());
        }
        return cartItemRepository.save(cartItem);
    }

    public List<CartItemResponse> getCartItemByCartId(int cartId) {
        List<CartItem> listCartItems = cartItemRepository.findByCartId(cartId);

        List<CartItemResponse> listCartItemResponses = new ArrayList<>();

        for (CartItem item : listCartItems) {
            ProductResponse product = productClient.getProductById(item.getProductId()); // gọi đến service khác

            CartItemResponse cartItemResponse = new CartItemResponse(
                    item.getCartItemsId(),
                    item.getProductId(),
                    product.getProductImageUrl(),
                    product.getProductName(),
                    product.getProductPrice(),
                    item.getQuantity()
            );

            listCartItemResponses.add(cartItemResponse);
        }

        return listCartItemResponses;
    }

    public Cart getCartByUserId(@PathVariable String userId) {
        Cart cart = cartRepository.getCartsByUserId(userId)
                .orElseThrow(()-> new RuntimeException(" không tìm thấy cart"));
        return cart;
    }

    public CartItem updateCartItemQuantity(CartItemUpdateRequest request, @PathVariable int cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(()->new RuntimeException(" không tìm thấy cartItem"));
        cartItem.setQuantity(request.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    public String deleteCartItemByCartId(int cartItemId){
        cartItemRepository.deleteById(cartItemId);
        return "cartItem deleted successfully";
    }

}
