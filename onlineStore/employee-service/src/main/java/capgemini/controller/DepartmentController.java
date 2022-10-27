package capgemini.controller;

import capgemini.dto.DepartmentRequest;
import capgemini.dto.DepartmentResponse;
import capgemini.exception.DepartmentNotFoundException;
import capgemini.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PostMapping
    public void register(@RequestBody DepartmentRequest departmentRequest) {
        departmentService.register(departmentRequest);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws DepartmentNotFoundException {
        departmentService.update(id, fields);
    }


    //Informational Queries
    @GetMapping
    public List<DepartmentResponse> findAll() {
        return departmentService.findAll();
    }

    @GetMapping(params = {"{id}"})
    public DepartmentResponse findbyID(@PathVariable int id) throws DepartmentNotFoundException {
        return departmentService.findById(id);
    }

    @GetMapping(params = {"{name}"})
    public List<DepartmentResponse> findbyName(@RequestParam String name) {
        return departmentService.findByName(name);
    }
}

