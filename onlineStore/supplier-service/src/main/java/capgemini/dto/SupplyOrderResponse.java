package capgemini.dto;

import capgemini.model.Supplier;
import capgemini.model.SupplierOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplyOrderResponse {

    private int id;

    private String number;

    private List<OrderItem> listOrderItems = new ArrayList<>();

    private Supplier supplier;

    private SupplierOrderStatus supplierOrderStatus;
}
