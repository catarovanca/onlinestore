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
public class ProductImageResponse {

    private int id;

    private String name;

    private String description;

    private byte[] image;

    private String imageType;

    private Product product;
}
