package capgemini.onlinestore;

import capgemini.onlinestore.model.Courier;
import capgemini.onlinestore.persistence.CourierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CourierRepositoryTest {

    @Autowired
    CourierRepository courierRepository;

    @Test
    void findbyId_CategoryExist_succes() {
        Courier courier = new Courier();
        courier.setName("FAN Courier");
        courier.setTown("Bucharest");

        courierRepository.save(courier);
        assertTrue(courier.getId() > 0);
    }

    @Test
    void findByName_CategoryExist_succes(){
       Courier courier = new Courier();
       // I have to add all of properties because Not Null constraints
        courier.setName("TNT Courier");
        courier.setEmailAddress("probe@yahoo.com");
        courier.setPhoneNumber("0755666777");
        courier.setAddress1("str.Zorilor nr.12");
        courier.setTown("Iasi");
        courier.setCounty("Iasi");
        courier.setPostCode("086557");

        courierRepository.save(courier);
        assertTrue(courier.getId() > 0);

        Courier courier1 = courierRepository.findByName("TNT Courier").orElseThrow();
        assertNotNull(courier1);
        assertEquals(courier.getName(),courier1.getName());
    }
}
