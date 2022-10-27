package capgemini.controller;

import capgemini.dto.ProductRequest;
import capgemini.dto.ProductResponse;
import capgemini.exception.ProductNotFoundException;
import capgemini.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PostMapping
    public void register(@RequestBody ProductRequest productRequest){
        productService.register(productRequest);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws ProductNotFoundException {
        productService.update(id, fields);
    }



    //Informational Queries
    @GetMapping()
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @GetMapping(params = {"id"})
    public ProductResponse findbyID(@PathVariable("id") int id) throws ProductNotFoundException {
        return productService.findById(id);
    }

    @GetMapping(params = {"name"})
    public List<ProductResponse> findByName(@RequestParam("name") String name) {
        return productService.findByName(name);
    }

    @GetMapping(params = {"brand"})
    public List<ProductResponse> findByBrand(@RequestParam("brand") String brand) {
        return productService.findByBrand(brand);
    }

    @GetMapping("/{categoryID}/products")
    public List<ProductResponse> findbyCategory(@PathVariable("categoryID") int id) {
        return productService.findByCategoryID(id);
    }

    //Stock Queries
    @GetMapping(params = {"quantity"})
    public List<ProductResponse> stockLessThanOrEqual(@RequestParam("quantity") int quantity) {
        return productService.stockLessThanOrEqual(quantity);
    }

    @GetMapping(params = {"categoryID", "quantity"})
    public List<ProductResponse> stockLessThanOrEqualForCategory(@PathVariable("categoryID") int id,
                                                                 @RequestParam("quantity") int quantity) {
        return productService.stockLessThanOrEqualForCategory(id, quantity);
    }

    //Product Images Queries
    @GetMapping("/{id}/images")
    public List<ProductResponse> findByProductImages_Product_Id(@PathVariable("id") int id) {
        return productService.findByProductImages_Product_Id(id);
    }


    //Product Rewview Queries
    @GetMapping("/{id}/reviews")
    public List<ProductResponse> findByReviews_Product_Id(@PathVariable("id") int id){
        return productService.findByReviews_Product_Id(id);
    }


    /*
    @DeleteMapping("/{id}")
    public ProductResponse deleteById(@PathVariable int id) throws ProductNotFoundException {
        return productService.deleteById(id);
    }
 */
}
