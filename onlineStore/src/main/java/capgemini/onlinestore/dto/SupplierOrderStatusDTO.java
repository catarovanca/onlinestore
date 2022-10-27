package capgemini.onlinestore.dto;


import capgemini.onlinestore.model.OrderStatusName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierOrderStatusDTO {

    private int id;

    private OrderStatusName orderStatusName;

    private String description;
}
