package capgemini.onlinestore.dto;


import capgemini.onlinestore.model.OrderItem;
import capgemini.onlinestore.model.Supplier;
import capgemini.onlinestore.model.SupplierOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplyOrderDTO {

    private int id;

    private String number;

    private List<OrderItem> orderItem = new ArrayList<>();

    private Supplier supplier;

    private SupplierOrderStatus supplierOrderStatus;
}
