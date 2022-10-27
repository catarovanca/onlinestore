package capgemini.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {

    private String id;

    private int product;

    private double price;

    private int quantity;

    private double itemTotal;


}
