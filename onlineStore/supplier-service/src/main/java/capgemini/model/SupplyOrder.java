package capgemini.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class SupplyOrders contain info. about id and number
 * Contains also the relations between tables
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SupplyOrder {

    @Id
    @GeneratedValue
    private int id;

    private String number;

    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderItem> listOrderItems = new ArrayList<>();

    @OneToOne
    private Supplier supplier;

    @OneToOne
    private SupplierOrderStatus supplierOrderStatus;

}
