package capgemini.persistence;

import capgemini.model.Supplier;
import capgemini.model.SupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Integer> {

    Optional<SupplyOrder> findBySupplier(Supplier supplier);

    List<SupplyOrder> findByName(String name);
}
