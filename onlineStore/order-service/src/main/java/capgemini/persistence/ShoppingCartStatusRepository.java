package capgemini.persistence;


import capgemini.model.ShoppingCartStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartStatusRepository extends MongoRepository<ShoppingCartStatus, String> {
}
