package capgemini.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class Rewiews contains comments from customers and rating.
 * Is found also connection between tables
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REVIEWS", indexes = {
        @Index(name = "induserId", columnList = "USER_ID"),
        @Index(name = "indratingId", columnList = "RATING")
})
public class Review {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "COMMENTS", nullable = false)
    private String comments;

    @Column(name = "RATING", nullable = false)
    private int rating;

    @JsonBackReference //Needed to prevent a JSON Endless Loop Issue
    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    //Will return email for user from Customer Microservice
    @Column(name = "USER_ID", nullable = false)
    private String user;

}

