package capgemini.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * The store has a wide variety of products and they are selected by category:
 * food, garden,clothes,etc.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CATEGORIES",indexes = @Index(name = "indCategory_name",columnList = "name"))
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "CATEGORIES_ID")
    private int id;

    @Column(nullable = false)
    private String name;

    private String description;

}
