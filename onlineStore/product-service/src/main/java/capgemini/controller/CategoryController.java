package capgemini.controller;

import capgemini.dto.CategoryRequest;
import capgemini.dto.CategoryResponse;
import capgemini.exception.CategoryNotFoundException;
import capgemini.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PostMapping
    public void register(@RequestBody CategoryRequest categoryRequest){
        categoryService.register(categoryRequest);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws CategoryNotFoundException {
        categoryService.update(id, fields);
    }



    //Informational Queries
    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @GetMapping(params = {"{id}"})
    public CategoryResponse findbyID(@PathVariable int id) throws CategoryNotFoundException {
        return categoryService.findById(id);
    }

    @GetMapping(params = {"{name}"})
    public List<CategoryResponse> findbyName(@RequestParam String name) {
        return categoryService.findByName(name);
    }
}


