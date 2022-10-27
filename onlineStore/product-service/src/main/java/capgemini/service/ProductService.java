package capgemini.service;

import capgemini.dto.ProductRequest;
import capgemini.dto.ProductResponse;
import capgemini.exception.ProductNotFoundException;
import capgemini.model.Product;
import capgemini.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private ProductResponse convertEntitytoDTO(Product product){
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
        return productResponse;
    }

    private List<ProductResponse> convertEntitytoDTO(List<Product> product){
        return product.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private Product convertDTOtoEntity(ProductRequest productRequest){
        Product product = modelMapper.map(productRequest, Product.class);
        return product;
    }

    private Product convertDTOtoEntity(ProductResponse productResponse){
        Product product = modelMapper.map(productResponse, Product.class);
        return product;
    }


    //CRUD Operations

    public void register(ProductRequest productRequest) {
        Product product = convertDTOtoEntity(productRequest);
        productRepository.save(product);
    }

    public void update(int id, Map<Object, Object> fields) throws ProductNotFoundException {
        ProductResponse existingProduct = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(ProductResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingProduct, value);
        });
        Product product = convertDTOtoEntity(existingProduct);
        productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }


    //Informational Queries
    //List All the Products in the database
    public List<ProductResponse> findAll(){
        List<Product> products = productRepository.findAll();
        return convertEntitytoDTO(products);
    }

    public ProductResponse findById(int id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(product);
    }
    public List<ProductResponse> findByName(String name) {
        List<Product> product = productRepository.findByNameContainingIgnoreCase(name);
        return convertEntitytoDTO(product);
    }

    public List<ProductResponse> findByBrand(String brand) {
        List<Product> product = productRepository.findByBrandContainingIgnoreCase(brand);
        return convertEntitytoDTO(product);
    }

    public List<ProductResponse> findByCategoryID(int id) {
        List<Product> product = productRepository.findByCategory_Id(id);
        return convertEntitytoDTO(product);
    }


    public List<ProductResponse> stockLessThanOrEqual (int quantity){
        List<Product> products = productRepository.findByQuantityLessThanEqual(quantity);
        return convertEntitytoDTO(products);
    }

    public List<ProductResponse> stockLessThanOrEqualForCategory (int id, int quantity){
        List<Product> products = productRepository.findByCategory_IdAndQuantityLessThanEqual(id, quantity);
        return convertEntitytoDTO(products);
    }

    public List<ProductResponse> findByProductImages_Product_Id(int id){
        List<Product> productImages = productRepository.findByProductImages_Product_Id(id);
        return convertEntitytoDTO(productImages);
    }

    public List<ProductResponse> findByReviews_Product_Id(int id){
        List<Product> products = productRepository.findByReviews_Product_Id(id);
        return  convertEntitytoDTO(products);
    }


}