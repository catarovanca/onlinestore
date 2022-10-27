package capgemini.dto;

import capgemini.model.Category;
import capgemini.model.ProductImage;
import capgemini.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private int id;

    private String name;

    private String description;

    private String detailedDescription;

    private String brand;

    private int quantity;

    private double price;

    private String warranty;

    private List<Review> reviews;

    private List<ProductImage> productImages;

    private Category category;
}