package capgemini.persistence;

import capgemini.model.SupplierEmployeeContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierEmployeeContactRepository extends JpaRepository<SupplierEmployeeContact, Integer> {

    Optional<SupplierEmployeeContact> findByEmailAddress(String name);

    List<SupplierEmployeeContact> findByFirstNameContainsIgnoreCase(String firstName);


}
