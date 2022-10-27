package capgemini.dto;

import capgemini.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierEmployeeContactRequest {

    private String emailAddress;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address1;

    private String address2;

    private String county;

    private String town;

    private String postCode;

    private Supplier supplier;
}

