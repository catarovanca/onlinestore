package capgemini.service;

import capgemini.dto.ProductImageRequest;
import capgemini.dto.ProductImageResponse;
import capgemini.model.ProductImage;
import capgemini.exception.ProductImageNotFoundException;
import capgemini.persistence.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private ProductImageResponse convertEntitytoDTO(ProductImage productImage){
        ProductImageResponse productImageResponse = modelMapper.map(productImage, ProductImageResponse.class);
        return productImageResponse;
    }

    private List<ProductImageResponse> convertEntitytoDTO(List<ProductImage> productImageList){
        return productImageList.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private ProductImage convertDTOtoEntity(ProductImageRequest productImageRequest){
        ProductImage productImage = modelMapper.map(productImageRequest, ProductImage.class);
        return productImage;
    }

    private ProductImage convertDTOtoEntity(ProductImageResponse productImageResponse){
        ProductImage productImage = modelMapper.map(productImageResponse, ProductImage.class);
        return productImage;
    }

    //CRUD
    public void register(MultipartFile productImageFile, ProductImageRequest productImageRequest) throws IOException {
        productImageRequest.setImage(productImageFile.getBytes());
        productImageRequest.setImageType(productImageFile.getContentType());
        ProductImage productImage = convertDTOtoEntity(productImageRequest);
        productImageRepository.save(productImage);
    }

    // Update productImage
    public void update(int id, MultipartFile imageFile, Map<Object, Object> fields) throws ProductImageNotFoundException, IOException {
        ProductImageResponse existingProductImage = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(ProductImageResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingProductImage, value);
        });

        if(imageFile != null ){
            existingProductImage.setImage(imageFile.getBytes());
            existingProductImage.setImageType(imageFile.getContentType());
        }

        ProductImage productImage = convertDTOtoEntity(existingProductImage);
        productImageRepository.save(productImage);
    }


    //Informational Queries

    // List of productImages
    public List<ProductImageResponse> getProductImages() {
        List<ProductImage> productImageList = productImageRepository.findAll();
        return convertEntitytoDTO(productImageList);
    }

    // Get specific productImage
    public ProductImageResponse findById(int id) throws ProductImageNotFoundException {
        ProductImage productImage = productImageRepository.findById(id).orElseThrow(() ->
                new ProductImageNotFoundException("ProductImage with the id: " + id + " doesn't exist!"));
        return convertEntitytoDTO(productImage);
    }

    public List<ProductImageResponse> findByNameContainingIgnoreCase(String name){
        List<ProductImage> productImageList = productImageRepository.findByNameContainingIgnoreCase(name);
        return convertEntitytoDTO(productImageList);
    }

    public List<ProductImageResponse> getByProductId(int id){
        List<ProductImage> productImage = productImageRepository.findByProduct_Id(id);
        return convertEntitytoDTO(productImage);
    }

}