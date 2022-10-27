package capgemini.persistence;

import capgemini.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourierRepository extends JpaRepository<Courier, Integer> {
    List<Courier> findByNameContainingIgnoreCase(String name);
}