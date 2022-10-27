package capgemini.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

/**
 *  This class stores the departments in the organisation
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "DEPARTMENTS", indexes = @Index(name = "indName", columnList = "NAME"))
public class Department {

    @Id
    @GeneratedValue
    @Column(name = "DEPARTMENT_ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

}
