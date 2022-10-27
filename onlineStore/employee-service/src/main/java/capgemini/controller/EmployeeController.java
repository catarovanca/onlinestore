package capgemini.controller;

import capgemini.dto.EmployeeRequest;
import capgemini.dto.EmployeeResponse;
import capgemini.exception.EmployeeNotFoundException;
import capgemini.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PostMapping
    public void register(@RequestBody EmployeeRequest employeeRequest){
        employeeService.register(employeeRequest);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws EmployeeNotFoundException {
        employeeService.update(id, fields);
    }



    //Informational Queries
    @GetMapping
    public List<EmployeeResponse> findAll() {
        return employeeService.findAll();
    }

    @GetMapping(params = {"{id}"})
    public EmployeeResponse findbyID(@PathVariable int id) throws EmployeeNotFoundException {
        return employeeService.findById(id);
    }

    @GetMapping(params = {"{name}"})
    public List<EmployeeResponse> findbyName(@RequestParam String name) {
        return employeeService.findByName(name);
    }
}