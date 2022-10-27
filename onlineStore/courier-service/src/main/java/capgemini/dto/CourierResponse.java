package capgemini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourierResponse {

    private int id;

    private String name;

    private String emailAddress;

    private String phoneNumber;

    private String address1;

    private String address2;

    private String county;

    private String town;

    private String postCode;
}
