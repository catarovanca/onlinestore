package capgemini.onlinestore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {

    private int id;

    private String name;

    private String emailAddress;

    private String address1;

    private String address2;

    private String phoneNumber;

    private String county;

    private String town;

    private String postCode;

}

