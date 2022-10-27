package capgemini.service;

import capgemini.dto.DepartmentRequest;
import capgemini.dto.DepartmentResponse;
import capgemini.exception.DepartmentNotFoundException;
import capgemini.model.Department;
import capgemini.persistence.DepartmentRepository;
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
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Informational Queries
    //List All the Departments in the database
    public List<DepartmentResponse> findAll(){
        List<Department> departments = departmentRepository.findAll();
        return convertEntitytoDTO(departments);
    }

    public DepartmentResponse findById(int id) throws DepartmentNotFoundException {
        Department department = departmentRepository.findById(id).orElseThrow(() ->
                new DepartmentNotFoundException("Department with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(department);
    }
    public List<DepartmentResponse> findByName(String name) {
        List<Department> department = departmentRepository.findByNameContainingIgnoreCase(name);
        return convertEntitytoDTO(department);
    }


    // CRUD
    public void register(DepartmentRequest departmentRequest) {
        Department department = convertDTOtoEntity(departmentRequest);
        departmentRepository.save(department);
    }

    public void update(int id, Map<Object, Object> fields) throws DepartmentNotFoundException {
        DepartmentResponse existingDepartment = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(DepartmentResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingDepartment, value);
        });
        Department department = convertDTOtoEntity(existingDepartment);
        departmentRepository.save(department);
    }

    public void delete(int id) {
        departmentRepository.deleteById(id);
    }



    //Mappers
    private DepartmentResponse convertEntitytoDTO(Department department){
        DepartmentResponse departmentResponse = modelMapper.map(department, DepartmentResponse.class);
        return departmentResponse;
    }

    private List<DepartmentResponse> convertEntitytoDTO(List<Department> department){
        return department.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private Department convertDTOtoEntity(DepartmentRequest departmentRequest){
        Department department = modelMapper.map(departmentRequest, Department.class);
        return department;
    }

    private Department convertDTOtoEntity(DepartmentResponse departmentResponse){
        Department department = modelMapper.map(departmentResponse, Department.class);
        return department;
    }
}
