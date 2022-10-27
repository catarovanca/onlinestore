package capgemini.controller;

import capgemini.dto.SupplierRequest;
import capgemini.dto.SupplierResponse;
import capgemini.exception.SupplierNotFoundException;
import capgemini.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PostMapping
    public void register(@RequestBody SupplierRequest supplierRequest){
        supplierService.register(supplierRequest);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws SupplierNotFoundException {
        supplierService.update(id, fields);
    }



    //Informational Queries
    @GetMapping
    public List<SupplierResponse> findAll() {
        return supplierService.findAll();
    }

    @GetMapping(params = {"{id}"})
    public SupplierResponse findbyID(@PathVariable int id) throws SupplierNotFoundException {
        return supplierService.findById(id);
    }

    @GetMapping(params = {"{name}"})
    public List<SupplierResponse> findbyName(@RequestParam String name) {
        return supplierService.findByName(name);
    }
}