package capgemini.service;

import capgemini.dto.EmployeeRequest;
import capgemini.dto.EmployeeResponse;
import capgemini.exception.EmployeeNotFoundException;
import capgemini.model.Employee;
import capgemini.persistence.EmployeeRepository;
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
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;


    //Informational Queries
    //List All the Employees in the database
    public List<EmployeeResponse> findAll(){
        List<Employee> employee = employeeRepository.findAll();
        return convertEntitytoDTO(employee);
    }

    public EmployeeResponse findById(int id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException("Employee with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(employee);
    }
    public List<EmployeeResponse> findByName(String firstName) {
        List<Employee> employees = employeeRepository.findByFirstNameContainsIgnoreCase(firstName);
        return convertEntitytoDTO(employees);
    }



    //CRUD
    public void register(EmployeeRequest employeeRequest) {
        Employee employee = convertDTOtoEntity(employeeRequest);
        employeeRepository.save(employee);
    }

    public void update(int id, Map<Object, Object> fields) throws EmployeeNotFoundException {
        EmployeeResponse existingEmployee = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(EmployeeResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingEmployee, value);
        });
        Employee employee = convertDTOtoEntity(existingEmployee);
        employeeRepository.save(employee);
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }




    //Mappers
    private EmployeeResponse convertEntitytoDTO(Employee employee){
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        return employeeResponse;
    }

    private List<EmployeeResponse> convertEntitytoDTO(List<Employee> employee){
        return employee.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private Employee convertDTOtoEntity(EmployeeRequest employeeRequest){
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        return employee;
    }

    private Employee convertDTOtoEntity(EmployeeResponse employeeResponse){
        Employee employee = modelMapper.map(employeeResponse, Employee.class);
        return employee;
    }
}