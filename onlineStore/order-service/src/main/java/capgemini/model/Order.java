package capgemini.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class dedicated for client orders.
 * It contains details about order and delivery date, amount of ordered products.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order_db")
public class Order {

    @Id
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Indexed
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate deliveryDate;

    @Indexed
    @Email
    private String customer;

    @Indexed
    private int billingAddress;

    @Indexed
    private int deliveryAddress;

    private List<OrderItem> listOrderItems;

    @Indexed
    private int courier;

    private double deliveryCharge;

    private double totalValue;

    @Indexed
    private String listOrderStatus;


}
