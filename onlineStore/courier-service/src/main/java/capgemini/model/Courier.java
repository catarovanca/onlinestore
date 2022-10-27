package capgemini.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class contains billing information and delivery location.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "COURIERS", indexes = @Index(name = "indCourier_name", columnList = "name"))
public class Courier {

    @Id
    @GeneratedValue
    @Column(name = "COURIER_ID")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address1;

    private String address2;

    @Column(nullable = false)
    private String county;

    @Column(nullable = false)
    private String town;

    @Column(nullable = false)
    private String postCode;
}
