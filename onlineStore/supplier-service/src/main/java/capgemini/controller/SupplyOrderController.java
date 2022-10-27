package capgemini.controller;

import capgemini.dto.SupplyOrderRequest;
import capgemini.dto.SupplyOrderResponse;
import capgemini.exception.SupplyOrderNotFoundException;
import capgemini.service.SupplyOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/supplyOrders")
public class SupplyOrderController {

    @Autowired
    private SupplyOrderService supplyOrderService;


    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PostMapping
    public void register(@RequestBody SupplyOrderRequest supplyOrderRequest){
        supplyOrderService.register(supplyOrderRequest);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws SupplyOrderNotFoundException {
        supplyOrderService.update(id, fields);
    }



    //Informational Queries
    @GetMapping
    public List<SupplyOrderResponse> findAll() {
        return supplyOrderService.findAll();
    }

    @GetMapping(params = {"{id}"})
    public SupplyOrderResponse findbyID(@PathVariable int id) throws SupplyOrderNotFoundException {
        return supplyOrderService.findById(id);
    }

    @GetMapping(params = {"{name}"})
    public List<SupplyOrderResponse> findbyName(@RequestParam String name) {
        return supplyOrderService.findByName(name);
    }
}