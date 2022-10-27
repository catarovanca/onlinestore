package capgemini.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class UserTypes is created to distinguish between CustomerUser and EmployeeUser.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERTYPES", indexes = @Index(name = "idUser_Type_name", columnList = "USER_TYPE_NAME"))
public class UserType {

    @Id
    @Column(name = "USER_TYPE_ID")
    @GeneratedValue
    private int id;

    @Column(name = "USER_TYPE_NAME",nullable = false)
    private String name;

    @Column(name = "USER_TYPE_DESCRIPTION",nullable = false)
    private String description;



}
