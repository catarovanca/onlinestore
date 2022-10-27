package capgemini.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This class ShoppingCart it contains details about type,
 * quantity and total value of product chosen by the customer
 * The class it contains also delivery charge
 * and start/expireDate
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("shoppingcarts_db")
public class ShoppingCart {

    @Id
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

    //TODO: Set this value to 10 days after creation date (createdAt)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime cartExpiryDate;

    @Indexed
    @Email
    private String customer;

    @Indexed
    private int billingAddress;

    @Indexed
    private int deliveryAddress;

    private List<OrderItem> listOrderItems;

    private double deliveryCharge;

    private double totalValue;

    @Indexed
    private String shoppingCartStatus;
}
