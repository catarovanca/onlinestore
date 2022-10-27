package capgemini.dto;

import capgemini.model.OrderItem;
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
public class OrderRequest {

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate deliveryDate;

    private String customer;

    private int billingAddress;

    private int deliveryAddress;

    private List<OrderItemRequest> listOrderItems;

    private int courier;

    private double deliveryCharge;

    private double totalValue;

    private String listOrderStatus;

}