package capgemini.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * this class contains descriptive details of product
 * and one or more images of product.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTIMAGES", indexes = {
        @Index(name = "indproductId", columnList = "PRODUCT_ID"),
        @Index(name = "indName", columnList = "PRODUCT_IMAGE_NAME")
})

public class ProductImage {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "PRODUCT_IMAGE_NAME", nullable = false)
    private String name;

    @Column(name = "PRODUCT_IMAGE_DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PRODUCT_IMAGE", nullable = false)
    private byte[] image;

    @Column(name="IMAGE_TYPE", nullable = false)
    private String imageType;

    @JsonBackReference //Needed to prevent a JSON Endless Loop Issue
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;
}
