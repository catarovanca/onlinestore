package capgemini.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 *this class give us information about SupplierOrderStatus.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SupplierOrderStatus {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "NAME", nullable = false)
    private int orderStatusName;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

}
