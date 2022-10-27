package capgemini.persistence;

import capgemini.model.SupplierOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierOrderStatusRepository extends JpaRepository<SupplierOrderStatus, Integer> {

    List<SupplierOrderStatus> findByOrderStatusName(String name);
}
