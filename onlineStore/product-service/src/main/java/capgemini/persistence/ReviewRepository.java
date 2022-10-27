package capgemini.persistence;

import capgemini.model.Category;
import capgemini.model.Product;
import capgemini.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByRating(int rating);





}
