package capgemini.controller;


import capgemini.dto.ProductImageRequest;
import capgemini.dto.ProductImageResponse;
import capgemini.exception.ProductImageNotFoundException;
import capgemini.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/productimages")
public class ProductImagesController {

    @Autowired
    ProductImageService productImageService;

    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PostMapping(value = "/", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestPart("file") MultipartFile file, @RequestPart("body") ProductImageRequest productImageRequest) throws IOException {
        productImageService.register(file, productImageRequest);
    }

    @PatchMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void update(@PathVariable int id, @RequestPart(value = "file", required = false) MultipartFile file, @RequestPart(value = "body", required = false) Map<Object, Object> fields) throws ProductImageNotFoundException, IOException {
        productImageService.update(id, file, fields);
    }


    //Informational Queries
    @GetMapping
    public List<ProductImageResponse> getProductImages(){
        return productImageService.getProductImages();
    }

    @GetMapping(params = {"id"})
    public ProductImageResponse findById(@PathVariable int id) throws ProductImageNotFoundException {
        return productImageService.findById(id);
    }

    @GetMapping(params = {"name"})
    public List<ProductImageResponse> findByNameContainingIgnoreCase(@RequestParam("name") String name){
        return productImageService.findByNameContainingIgnoreCase(name);
    }

    @GetMapping(value = "/{productId}/images")
    List<ProductImageResponse> getByProductId(@PathVariable int productId) {
        return productImageService.getByProductId(productId);
    }

}