package Myproject.cart_service.service;

import Myproject.cart_service.dto.request.CartCreationRequest;
import Myproject.cart_service.dto.request.CartUpdateRequest;
import Myproject.cart_service.entity.Cart;
import Myproject.cart_service.mapper.CartMapper;
import Myproject.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service

public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper  cartMapper;


    public List<Cart> getAllCarts(){
        return  cartRepository.findAll();
    }

    public Cart  getCartById(int  cartId){
        return cartRepository.getByCartId(cartId);
    }

    public Cart addCart(@RequestBody CartCreationRequest request){
        Cart cart = new Cart();
        cart = cartMapper.toCart(request);
        return cartRepository.save(cart);
    }

    public Cart updateCart(int cartId, @RequestBody CartUpdateRequest request){
        Cart cart=cartRepository.getByCartId(cartId);
        cart= cartMapper.toUpdateCart(request);
        return cart;
    }

    public String deleteCart(int cartId){
        Cart cart=cartRepository.getByCartId(cartId);
        cartRepository.delete(cart);
        return "Cart deleted";
    }

}
