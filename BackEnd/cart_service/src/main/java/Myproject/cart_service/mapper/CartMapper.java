package Myproject.cart_service.mapper;


import Myproject.cart_service.dto.request.CartCreationRequest;
import Myproject.cart_service.dto.request.CartUpdateRequest;
import Myproject.cart_service.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "cartId" ,ignore = true)
    Cart toCart (CartCreationRequest request);
    Cart toUpdateCart(CartUpdateRequest request);

}
