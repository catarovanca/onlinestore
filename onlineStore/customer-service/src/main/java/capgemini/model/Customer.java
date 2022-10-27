package capgemini.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class CustomerUser contain billing information
 * and delivery address details.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMERS", indexes = {
        @Index(name = "indBillingFirstName", columnList = "BILLING_FIRST_NAME"),
        @Index(name = "indBillingLastName", columnList = "BILLING_LAST_NAME"),
        @Index(name = "indDeliveryFirstName", columnList = "DELIVERY_FIRST_NAME"),
        @Index(name = "indDeliveryLastName", columnList = "DELIVERY_LAST_NAME"),
})
public class Customer {

    @Id
    @Column(name = "EMAIL_ADDRESS")
    @NotNull
    @Email
    private String emailAddress;

    @Size(min = 8,max = 21)
    @Column(name = "CUSTOMER_PASSWORD", nullable = false)
    private String password; //TODO: Encrypt Password

    @Column(name = "BILLING_FIRST_NAME", nullable = false)
    private String billingFirstName;

    @Column(name = "BILLING_LAST_NAME", nullable = false)
    private String billingLastName;

    @Column(name = "BILLING_PHONE_NUMBER", nullable = false)
    private String billingPhoneNumber;

    @Column(name = "BILLING_ADDRESS_1", nullable = false)
    private String billingAddress1;

    @Column(name = "BILLING_ADDRESS_2")
    private String billingAddress2;

    @Column(name = "BILLING_COUNTY", nullable = false)
    private String billingCounty;

    @Column(name = "BILLING_TOWN", nullable = false)
    private String billingTown;

    @Column(name = "BILLING_POST_CODE", nullable = false)
    private String billingPostCode;

    @Column(name = "DELIVERY_FIRST_NAME", nullable = false)
    private String deliveryFirstName;

    @Column(name = "DELIVERY_LAST_NAME", nullable = false)
    private String deliveryLastName;

    @Column(name = "DELIVERY_PHONE_NUMBER", nullable = false)
    private String deliveryPhoneNumber;

    @Column(name = "DELIVERY_ADDRESS_1", nullable = false)
    private String deliveryAddress1;

    @Column(name = "DELIVERY_ADDRESS_2")
    private String deliveryAddress2;

    @Column(name = "DELIVERY_COUNTY", nullable = false)
    private String deliveryCounty;

    @Column(name = "DELIVERY_TOWN", nullable = false)
    private String deliveryTown;

    @Column(name = "DELIVERY_POST_CODE", nullable = false)
    private String deliveryPostCode;

    @Column(name = "ACCOUNT_ACTIVE", nullable = false)
    private boolean accountActive = true;

}
