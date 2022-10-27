package capgemini.controller;


import capgemini.dto.UserTypeRequest;
import capgemini.dto.UserTypeResponse;
import capgemini.exception.UserTypeNotFoundException;
import capgemini.service.UserTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/userTypes")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PostMapping
    public void register(@RequestBody UserTypeRequest userTypeRequest){
        userTypeService.register(userTypeRequest);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws UserTypeNotFoundException {
        userTypeService.update(id, fields);
    }



    //Informational Queries
    @GetMapping
    public List<UserTypeResponse> findAll() {
        return userTypeService.findAll();
    }

    @GetMapping(params = {"{id}"})
    public UserTypeResponse findbyID(@PathVariable int id) throws UserTypeNotFoundException {
        return userTypeService.findById(id);
    }

    @GetMapping(params = {"{name}"})
    public List<UserTypeResponse> findbyName(@RequestParam String name) {
        return userTypeService.findByName(name);
    }
}