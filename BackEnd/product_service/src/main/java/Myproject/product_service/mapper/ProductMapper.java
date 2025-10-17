package Myproject.product_service.mapper;


import Myproject.product_service.entity.Product;
import Myproject.product_service.request.ProductCreationRequest;
import Myproject.product_service.request.ProductUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface ProductMapper {
    @Mapping(target = "productId" ,ignore = true)
    Product toProduct(ProductCreationRequest request);
    Product toUpdateProduct(ProductUpdateRequest request);

}
