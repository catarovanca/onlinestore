package capgemini.dto;

import capgemini.model.Department;
import capgemini.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private int id;

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

    private String positionTitle;

    private Department department;

    private UserType userType;

    private boolean accountActive;

}
