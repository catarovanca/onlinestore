package capgemini.dto;

import capgemini.model.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;

    private LocalDateTime createdAt;

    private LocalDate deliveryDate;

    private String customer;

    private int billingAddress;

    private int deliveryAddress;

    private List<OrderItemResponse> listOrderItems;

    private int courier;

    private double deliveryCharge;

    private double totalValue;

    private String listOrderStatus;
}