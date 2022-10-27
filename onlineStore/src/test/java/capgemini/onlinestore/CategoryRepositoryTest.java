package capgemini.onlinestore;

import capgemini.onlinestore.model.Category;
import capgemini.onlinestore.persistence.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.cdi.CdiRepositoryBean;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void findbyId_CategoryExist_succes(){
        Category category = new Category();
        category.setName("TV");
        category.setDescription("despre televizoare");

        categoryRepository.save(category);
        assertTrue(category.getId() >0);
    }

}
