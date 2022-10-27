package capgemini.service;


import capgemini.dto.CategoryRequest;
import capgemini.dto.CategoryResponse;
import capgemini.model.Category;
import capgemini.exception.CategoryNotFoundException;
import capgemini.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private CategoryResponse convertEntitytoDTO(Category category){
        CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
        return categoryResponse;
    }

    private List<CategoryResponse> convertEntitytoDTO(List<Category> category){
        return category.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private Category convertDTOtoEntity(CategoryRequest categoryRequest){
        Category category = modelMapper.map(categoryRequest, Category.class);
        return category;
    }

    private Category convertDTOtoEntity(CategoryResponse categoryResponse){
        Category category = modelMapper.map(categoryResponse, Category.class);
        return category;
    }


    //CRUD


    public void register(CategoryRequest categoryRequest) {
        Category category = convertDTOtoEntity(categoryRequest);
        categoryRepository.save(category);
    }

    public void update(int id, Map<Object, Object> fields) throws CategoryNotFoundException {
        CategoryResponse existingCategory = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(CategoryResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingCategory, value);
        });
        Category category = convertDTOtoEntity(existingCategory);
        categoryRepository.save(category);
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }


    //Informational Queries
    //List All the Categories in the database
    public List<CategoryResponse> findAll(){
        List<Category> categories = categoryRepository.findAll();
        return convertEntitytoDTO(categories);
    }

    public CategoryResponse findById(int id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(category);
    }
    public List<CategoryResponse> findByName(String name) {
        List<Category> category = categoryRepository.findByNameContainingIgnoreCase(name);
        return convertEntitytoDTO(category);
    }
}