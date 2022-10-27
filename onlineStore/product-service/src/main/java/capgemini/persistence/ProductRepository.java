package capgemini.persistence;

import capgemini.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByBrandContainingIgnoreCase(String brand);

    List<Product> findByCategory_Id(int id);

    List<Product> findByQuantityLessThanEqual(int quantity);

    List<Product> findByCategory_IdAndQuantityLessThanEqual(int id, int quantity);

    List<Product> findByProductImages_Product_Id(int id);

    List<Product> findByReviews_Product_Id(int id);

}
