package capgemini.controller;

import capgemini.dto.CourierRequest;
import capgemini.dto.CourierResponse;
import capgemini.exception.CourierNotFoundException;
import capgemini.service.CourierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/couriers")
public class CourierController {
        @Autowired
        private CourierService courierService;

    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.

    //CRUD
        @PostMapping
        public void register(@RequestBody CourierRequest courierRequest){
            courierService.register(courierRequest);
        }

        @PatchMapping("/{id}")
        public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws CourierNotFoundException {
            courierService.update(id, fields);
        }



        //Informational Queries
        @GetMapping
        public List<CourierResponse> findAll() {
            return courierService.findAll();
        }

        @GetMapping(params = {"{id}"})
        public CourierResponse findbyID(@PathVariable int id) throws CourierNotFoundException {
            return courierService.findById(id);
        }

        @GetMapping(params = {"{name}"})
        public List<CourierResponse> findbyName(@RequestParam String name) {
            return courierService.findByName(name);
        }
    }


