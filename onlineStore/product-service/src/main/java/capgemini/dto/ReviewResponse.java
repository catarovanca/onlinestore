package capgemini.dto;

import capgemini.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private int id;

    private String comments;

    private int rating;

    private Product product;

    private String user;
}
