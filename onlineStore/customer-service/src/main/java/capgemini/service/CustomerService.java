package capgemini.service;

import capgemini.dto.CustomerRequest;
import capgemini.dto.CustomerResponse;
import capgemini.exception.CustomerNotFoundException;
import capgemini.model.Customer;
import capgemini.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Informational Queries
    //List All the Customers in the database
    public List<CustomerResponse> findAll(){
        List<Customer> customers = customerRepository.findAll();
        return convertEntitytoDTO(customers);
    }
    public List<CustomerResponse> findByEmailAddressContainingIgnoreCase(String emailAddress){
        List<Customer> customer = customerRepository.findByEmailAddressContainingIgnoreCase(emailAddress);
        return convertEntitytoDTO(customer);
}
    public CustomerResponse findByEmailAddressEqualsIgnoreCase(String emailAddress){
        Customer customer = customerRepository.findByEmailAddressEqualsIgnoreCase(emailAddress);
        return convertEntitytoDTO(customer);
    }
    public CustomerResponse findByName(String name) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByName(name).orElseThrow(() ->
                new CustomerNotFoundException("Customer with the name: " + name + " doesn't exist!"));
        return convertEntitytoDTO(customer);
    }

    //CRUD
    public void register(CustomerRequest customerRequest) {
        Customer customer = convertDTOtoEntity(customerRequest);
        customerRepository.save(customer);
    }
    public void update(String emailAddress, Map<Object, Object> fields) throws CustomerNotFoundException {
        CustomerResponse existingCustomer = findByEmailAddressEqualsIgnoreCase(emailAddress);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(CustomerResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingCustomer, value);
        });
        Customer category = convertDTOtoEntity(existingCustomer);
        customerRepository.save(category);
    }
    public void delete(int id) {
        customerRepository.deleteById(id);
    }



    //Mappers
    private CustomerResponse convertEntitytoDTO(Customer customer){
        CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
        return customerResponse;
    }

    private List<CustomerResponse> convertEntitytoDTO(List<Customer> customer){
        return customer.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private Customer convertDTOtoEntity(CustomerRequest customerRequest){
        Customer customer = modelMapper.map(customerRequest, Customer.class);
        return customer;
    }

    private Customer convertDTOtoEntity(CustomerResponse customerResponse){
        Customer customer = modelMapper.map(customerResponse, Customer.class);
        return customer;
    }

}
