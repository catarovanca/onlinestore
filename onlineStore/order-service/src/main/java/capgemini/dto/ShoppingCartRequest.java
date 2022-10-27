package capgemini.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartRequest {


    private String customer;

    private int billingAddress;

    private int deliveryAddress;

    private List<OrderItemRequest> listOrderItems;

    private double deliveryCharge;

    private double totalValue;


}
