package Myproject.product_service.service;


import Myproject.product_service.Repository.ProductRepository;
import Myproject.product_service.entity.Product;
import Myproject.product_service.mapper.ProductMapper;
import Myproject.product_service.request.ProductCreationRequest;
import Myproject.product_service.request.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

//    lấy all danh sach san pham
    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        System.out.println(">>> Found products: " + products.size());
        return products;

    }

//    lấy sản phẩm theo id
    public Product getProductById(int productId){
        return productRepository.findById(productId);
    }

//tạo sản phẩm
    public Product createProduct(ProductCreationRequest request){
        Product product = new Product();
        product = productMapper.toProduct(request);
        return  productRepository.save(product);
    }

//    cập nhật thông tin sản phẩm
    public Product updateProduct(ProductUpdateRequest request , int productId){
        Product product = productRepository.findById(productId);
        product = productMapper.toUpdateProduct(request);
        return  productRepository.save(product);
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
