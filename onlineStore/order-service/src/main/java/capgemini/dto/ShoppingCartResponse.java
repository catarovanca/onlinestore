package capgemini.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartResponse {

    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime cartExpiryDate;

    private String customer;

    private int billingAddress;

    private int deliveryAddress;

    private List<OrderItemResponse> listOrderItems;

    private int courier;

    private double deliveryCharge;

    private double totalValue;

    private String shoppingCartStatus;

}
