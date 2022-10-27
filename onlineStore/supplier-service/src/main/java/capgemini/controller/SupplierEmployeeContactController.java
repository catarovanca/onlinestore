package capgemini.controller;

import capgemini.dto.SupplierEmployeeContactRequest;
import capgemini.dto.SupplierEmployeeContactResponse;
import capgemini.exception.SupplierEmployeeContactNotFoundException;
import capgemini.service.SupplierEmployeeContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/supplierEmployeeContacts")
public class SupplierEmployeeContactController {

    @Autowired
    private SupplierEmployeeContactService supplierEmployeeContactService;


    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PostMapping
    public void register(@RequestBody SupplierEmployeeContactRequest supplierEmployeeContactRequest){
        supplierEmployeeContactService.register(supplierEmployeeContactRequest);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws SupplierEmployeeContactNotFoundException {
        supplierEmployeeContactService.update(id, fields);
    }



    //Informational Queries
    @GetMapping
    public List<SupplierEmployeeContactResponse> findAll() {
        return supplierEmployeeContactService.findAll();
    }

    @GetMapping(params = {"{id}"})
    public SupplierEmployeeContactResponse findbyID(@PathVariable int id) throws SupplierEmployeeContactNotFoundException {
        return supplierEmployeeContactService.findById(id);
    }

    @GetMapping(params = {"{name}"})
    public List<SupplierEmployeeContactResponse> findbyName(@RequestParam String name) {
        return supplierEmployeeContactService.findByName(name);
    }
}