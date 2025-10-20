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
    @GetMapping("/getProductById/{productId}")
    public Product getProductById(@PathVariable int productId){
        return productService.getProductById(productId);
    }

//    thêm sản phẩm
    @PostMapping
    Product addProduct(@RequestBody ProductCreationRequest request){
        return productService.createProduct(request);
    }

//    sủa sản phẩm theo id
    @PutMapping("/updateProductById/{productId}")
    Product updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable("productId") int productId){
        return productService.updateProduct(request,productId);
    }


//    xóa sản phẩm theo id
    @DeleteMapping("/deleteProductById/{productId}")
    String deleteProduct(@PathVariable("productId") int productId){
        return productService.deleteProduct(productId);
    }

    // lọc sản phẩm theo hãng
    @GetMapping("/getProductByBrand/{productBrand}")
    List<Product> getProductByBrand(@PathVariable("productBrand") String productBrand){
        return productService.getProductByBrand(productBrand);
    }

//    lọc sản phâm theo giá tiền  GET /products/price-range?min=5000000&max=15000000
    @GetMapping("/getProductByPrice")
    List<Product> getProductByPrice(@RequestParam Double min, @RequestParam Double max){
        return productService.getProductByPrice(min,max);
    }

//lọc sản phẩm theo ram product
    @GetMapping("/getProductByRam/{productRam}")
    List<Product> getProductByRam(@PathVariable("productRam") int productRam){
        return productService.getProductByRam(productRam);
    }
    //lọc sản phẩm theo rom product
    @GetMapping("/getProductByRom/{productRom}")
    List<Product> getProductByRom(@PathVariable("productRom") int productRom){
        return productService.getProductByRom(productRom);
    }
// lọc sản phẩm theo màu sắc
    @GetMapping("/getProductByColor/{productColor}")
    List<Product> getProductByColor(@PathVariable("productColor") String productColor){
        return productService.getProductByColor(productColor);
    }

//    lọc sản phẩm theo kích thước màn hình
    @GetMapping("/getProductByScreenSize")
    List<Product> getProductByScreenSize(@RequestParam float min, @RequestParam float max){
        return productService.getProductByScreenSize(min,max);
    }
}
