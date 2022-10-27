package capgemini.model;

import lombok.*;


/**
 * Here we can find information about quantity and item product price.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private  int id;

    private int product;

    private double price;

    private int quantity;

    private double itemTotal;


}
