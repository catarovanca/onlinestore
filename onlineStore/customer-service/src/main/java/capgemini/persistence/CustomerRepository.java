package capgemini.persistence;

import capgemini.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where c.name LIKE :name")
    Optional<Customer> findByName(@Param("name") String name);

    List<Customer> findByEmailAddressContainingIgnoreCase(String emailAddress);

    List<Customer> findByBillingFirstNameContainingIgnoreCase(String billingFirstName);

    List<Customer> findByBillingLastNameContainingIgnoreCase(String billingLastName);

    List<Customer> findByDeliveryFirstNameContainingIgnoreCase(String deliveryFirstName);

    List<Customer> findByDeliveryLastNameContainingIgnoreCase(String deliveryLastName);

    Customer findByEmailAddressEqualsIgnoreCase(String emailAddress);






}
