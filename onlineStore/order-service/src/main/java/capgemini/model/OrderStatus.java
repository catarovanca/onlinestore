package capgemini.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * This class contains info. about order status and about the ordered products...
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("orderstatus_db")
public class OrderStatus {

    @Id
    private String id;

    @Indexed
    private String statusName;

    private String description;



}
