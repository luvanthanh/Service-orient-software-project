package Myproject.product_service.Repository;

import Myproject.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product,Integer>
{
   Product findById(int id);
   List<Product> findByProductBrand(String brand);
   List<Product> findByProductPriceBetween(Double minPrice, Double maxPrice);
}
