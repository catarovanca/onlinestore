package capgemini.onlinestore.dto;

import capgemini.onlinestore.model.Customer;
import capgemini.onlinestore.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private int id;

    private String comments;

    private String rating;

    private Product product;

    private Customer user;
}