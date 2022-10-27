package capgemini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequest {

    private String name;

    private String emailAddress;

    private String address1;

    private String address2;

    private String phoneNumber;

    private String county;

    private String town;

    private String postCode;
}
