package capgemini.onlinestore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDTO {

    private String emailAddress;

    private String billingFirstName;

    private String billingLastName;

    private String billingPhoneNumber;

    private String billingAddress1;

    private String billingAddress2;

    private String billingCounty;

    private String billingTown;

    private String billingPostCode;

    private String deliveryFirstName;

    private String deliveryLastName;

    private String deliveryPhoneNumber;

    private String deliveryAddress1;

    private String deliveryAddress2;

    private String deliveryCounty;

    private String deliveryTown;

    private String deliveryPostCode;

    private boolean accountActive;

}
