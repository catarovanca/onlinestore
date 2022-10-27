package capgemini.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * In class Product we can find all details about this like:
 * (name,description,detail,brand,etc),
 * also the connection between the tables.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS", indexes = {
        @Index(name = "indProduct_name", columnList = "NAME"),
        @Index(name = "indCategory", columnList = "CATEGORY_ID"),
        @Index(name = "indBrand", columnList = "BRAND"),
        @Index(name = "indQuantity",columnList = "QUANTITY")
})
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "DETAILED_DESCRIPTION", nullable = false, columnDefinition="TEXT")
    private String detailedDescription;

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "WARRANTY", nullable = false)
    private String warranty;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "product")
    private List<Review> reviews;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductImage> productImages;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;


}
