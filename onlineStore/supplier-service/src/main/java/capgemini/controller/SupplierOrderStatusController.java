package capgemini.controller;

import capgemini.dto.SupplierOrderStatusRequest;
import capgemini.dto.SupplierOrderStatusResponse;
import capgemini.exception.SupplierOrderStatusNotFoundException;
import capgemini.service.SupplierOrderStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/supplierOrderStatus")
public class SupplierOrderStatusController {

    @Autowired
    private SupplierOrderStatusService supplierOrderStatusService;


    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PostMapping
    public void register(@RequestBody SupplierOrderStatusRequest supplierOrderStatusRequest){
        supplierOrderStatusService.register(supplierOrderStatusRequest);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws SupplierOrderStatusNotFoundException {
        supplierOrderStatusService.update(id, fields);
    }



    //Informational Queries
    @GetMapping
    public List<SupplierOrderStatusResponse> findAll() {
        return supplierOrderStatusService.findAll();
    }

    @GetMapping(params = {"{id}"})
    public SupplierOrderStatusResponse findbyID(@PathVariable int id) throws SupplierOrderStatusNotFoundException {
        return supplierOrderStatusService.findById(id);
    }

    @GetMapping(params = {"{name}"})
    public List<SupplierOrderStatusResponse> findbyName(@RequestParam String name) {
        return supplierOrderStatusService.findByName(name);
    }
}