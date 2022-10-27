package capgemini.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class contains information contact about supplier
 * and relation between tables
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SUPPLIER")
public class Supplier {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL_ADDRESS", nullable = false)
    private String emailAddress;

    @Column(name = "ADDRESS_1", nullable = false)
    private String address1;

    @Column(name = "ADDRESS_2", nullable = false)
    private String address2;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "COUNTY", nullable = false)
    private String county;

    @Column(name = "TOWN", nullable = false)
    private String town;

    @Column(name = "POST_CODE", nullable = false)
    private String postCode;

}
