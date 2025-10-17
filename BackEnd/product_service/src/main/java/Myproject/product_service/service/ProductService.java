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





}
