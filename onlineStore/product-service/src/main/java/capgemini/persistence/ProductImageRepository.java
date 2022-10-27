package capgemini.persistence;

import capgemini.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    // Query to find all the images for a specific product
    List<ProductImage> findByProduct_Id(int id);

    List<ProductImage> findByNameContainingIgnoreCase(String name);
}
