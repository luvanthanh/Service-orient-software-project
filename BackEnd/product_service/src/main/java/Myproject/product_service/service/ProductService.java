package Myproject.product_service.service;


import Myproject.product_service.Repository.ProductRepository;
import Myproject.product_service.entity.Product;
import Myproject.product_service.mapper.ProductMapper;
import Myproject.product_service.request.ProductCreationRequest;
import Myproject.product_service.request.ProductUpdateRequest;
import Myproject.product_service.response.ProductResponse;
import Myproject.product_service.response.ResponseApi;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Builder

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

//    lấy all danh sach san pham
    public ResponseApi<List<Product>> getAllProducts(){
        List<Product> products = productRepository.findAll();
        System.out.println(">>> Found products: " + products.size());
        return ResponseApi.<List<Product>>builder()
                .code(1000)
                .message("lấy thành công")
                .data(products)
                .build();
    }

//    lấy sản phẩm theo id
    public ResponseApi<ProductResponse> getProductById(int productId){
        Product product = productRepository.findById(productId);
        ProductResponse productResponse = new ProductResponse();
        productResponse = productMapper.toProductResponse(product);
        return ResponseApi.<ProductResponse>builder()
                .code(1000)
                .message("đã lấy product thành công !")
                .data(productResponse)
                .build();
    }

//tạo sản phẩm
    public ResponseApi<Product> createProduct(ProductCreationRequest request){
        Product product = new Product();
        product = productMapper.toProduct(request);
        product = productRepository.save(product);
        return ResponseApi.<Product>builder()
                .code(1000)
                .message("đã tạo mới sản phẩm thành công !")
                .data(product)
                .build();
    }

//    cập nhật thông tin sản phẩm
    public ResponseApi<Product> updateProduct(ProductUpdateRequest request , int productId){
        Product product = productRepository.findById(productId);

        productMapper.updateProductFromRequest(request, product);

        Product productSave = productRepository.save(product);
        return ResponseApi.<Product>builder()
                .code(1000)
                .message("đã cập nhật thông tin Product thành! ")
                .data(productSave)
                .build();
    }


//    xoas sản phẩm  theo id
    public String deleteProduct(int productId){
        productRepository.deleteById(productId);
        return  "Deleted product with id: " + productId;
    }


//    lọc sản phẩm theo hãng
    public List<Product> getProductByBrand(String productBrand){
        return productRepository.findByProductBrand(productBrand);
    }

// lọc sản phẩm theo giá tiền
    public List<Product> getProductByPrice(double priceMin,  double priceMax){
        return productRepository.findByProductPriceBetween(priceMin, priceMax);
    }

//   lọc sản phẩm theo Ram
    public List<Product> getProductByRam(int ram){
        return productRepository.findByProductRam(ram);
    }

//    lọc sản phâm theo rom
    public List<Product> getProductByRom(int rom){
        return productRepository.findByProductRom(rom);
    }

//    lọc sanr phẩm theo màu sắc
    public List<Product> getProductByColor(String color){
        return productRepository.findByProductColor(color);
    }

//    locj sản phẩm theo kích thước màn hình
    public List<Product> getProductByScreenSize(float min, float max){
        return productRepository.findByProductScreenSizeBetween(min, max);
    }

}
