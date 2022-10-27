package capgemini.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * This class contains information contact about supplier
 * and relation between tables
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SupplierEmployeeContact {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "EMAIL_ADDRESS", nullable = false)
    private String emailAddress;

    @Size(min = 8,max = 18)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "ADDRESS_1", nullable = false)
    private String address1;

    @Column(name = "ADDRESS_2", nullable = false)
    private String address2;

    @Column(name = "COUNTY", nullable = false)
    private String county;

    @Column(name = "TOWN", nullable = false)
    private String town;

    @Column(name = "POST_CODE", nullable = false)
    private String postCode;

    @ManyToOne
    private Supplier supplier;
}
