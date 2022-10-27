package capgemini.controller;

import capgemini.dto.CustomerRequest;
import capgemini.dto.CustomerResponse;
import capgemini.exception.CustomerNotFoundException;
import capgemini.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    //TODO: Update code to support single REST service and all the filtering criteria should be query parameters.
    //CRUD
    @PatchMapping("/{emailAddress}")
    public void update(@PathVariable String emailAddress, @RequestBody Map<Object, Object> fields) throws CustomerNotFoundException {
        customerService.update(emailAddress, fields);
    }

    @PostMapping
    public void register(@RequestBody CustomerRequest customerRequest){
        customerService.register(customerRequest);
    }

    //Informational Queries
    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @GetMapping(params = {"{emailAddress}"})
    public List<CustomerResponse> findByEmailAddressContainingIgnoreCase(@PathVariable String emailAddress) {
        return customerService.findByEmailAddressContainingIgnoreCase(emailAddress);
    }

    @GetMapping(params = {"{firstName}"})
    public CustomerResponse findbyName(@RequestParam String firstName) throws CustomerNotFoundException {
        return customerService.findByName(firstName);
    }
}
