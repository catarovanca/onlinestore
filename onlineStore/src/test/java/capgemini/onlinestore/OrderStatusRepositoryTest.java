package capgemini.onlinestore;

import capgemini.onlinestore.model.OrderStatus;
import capgemini.onlinestore.model.OrderStatusName;
import capgemini.onlinestore.persistence.OrderStatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderStatusRepositoryTest {

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Test
    void findById_OrderStatusExist_succes() {

        OrderStatus orderStatusBeforeSave = new OrderStatus(1,OrderStatusName.ON_COURIER, "is on courier in delivery process");

        OrderStatus savedOrderStatus = orderStatusRepository.save(orderStatusBeforeSave);
        assertTrue(savedOrderStatus.getId() > 0);

        OrderStatus findedOrderStatus = orderStatusRepository.findById(savedOrderStatus.getId()).orElseThrow();
        assertNotNull(findedOrderStatus);
        assertEquals(savedOrderStatus.getId(),findedOrderStatus.getId());
    }
}

