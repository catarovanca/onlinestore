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
 * This class contain all personal info. about EmployeeUser,
 * his positionTitle in company and department.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEES", indexes = {
        @Index(name = "indFirstName",columnList = "FIRST_NAME"),
        @Index(name = "indLastName",columnList = "LAST_NAME"),
        @Index(name = "indEMailAddress",columnList = "FIRST_NAME"),
        @Index(name = "indDepartment",columnList = "DEPARTMENT_ID"),
        @Index(name = "indUserType", columnList = "USERTYPE_ID")
})
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID", nullable = false)
    private int id;

    //TODO: Investigate options to make sure it is unique - also method to autogenerate email using firstname.lastname@onlinestore.com
    @Column(name = "EMAIL_ADDRESS", nullable = false)
    @NotNull
    @Email
    private String emailAddress;

    @Size(min = 8, max = 21)
    @Column(name = "EMPLOYEE_PASSWORD", nullable = false)
    private String password; //TODO: Encrypt Password

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "ADDRESS_1", nullable = false)
    private String address1;

    @Column(name = "ADDRESS_2")
    private String address2;

    @Column(name = "COUNTY", nullable = false)
    private String county;

    @Column(name = "TOWN", nullable = false)
    private String town;

    @Column(name = "POST_CODE", nullable = false)
    private String postCode;

    @Column(name = "POSITION_TITLE", nullable = false)
    private String positionTitle;

    @OneToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @OneToOne
    @JoinColumn(name = "USERTYPE_ID")
    private UserType userType;

    @Column(name = "ACCOUNT_ACTIVE", nullable = false)
    private boolean accountActive;
}
