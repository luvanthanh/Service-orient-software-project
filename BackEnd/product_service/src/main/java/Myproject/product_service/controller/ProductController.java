package Myproject.product_service.controller;


import Myproject.product_service.entity.Product;
import Myproject.product_service.request.ProductCreationRequest;
import Myproject.product_service.request.ProductUpdateRequest;
import Myproject.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class ProductController {

    @Autowired
    private ProductService productService;


// Lấy tất cả product
    @GetMapping
    List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


//    lấy sản phẩm theo id
    @GetMapping("/getproduct/{productId}")
    public Product getProductById(@PathVariable int productId){
        return productService.getProductById(productId);
    }

//    thêm sản phẩm
    @PostMapping("/products")
    Product addProduct(@RequestBody ProductCreationRequest request){
        return productService.createProduct(request);
    }

//    sủa sản phẩm theo id
    @PutMapping("/{productId}")
    Product updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable("productId") int productId){
        return productService.updateProduct(request,productId);
    }

}
