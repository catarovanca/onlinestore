package capgemini.service;

import capgemini.dto.SupplierRequest;
import capgemini.dto.SupplierResponse;
import capgemini.exception.SupplierNotFoundException;
import capgemini.model.Supplier;
import capgemini.persistence.SupplierRepository;
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
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private SupplierResponse convertEntitytoDTO(Supplier supplier){
        SupplierResponse supplierResponse = modelMapper.map(supplier, SupplierResponse.class);
        return supplierResponse;
    }

    private List<SupplierResponse> convertEntitytoDTO(List<Supplier> suppliers){
        return suppliers.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private Supplier convertDTOtoEntity(SupplierRequest supplierRequest){
        Supplier supplier = modelMapper.map(supplierRequest, Supplier.class);
        return supplier;
    }

    private Supplier convertDTOtoEntity(SupplierResponse supplierResponse){
        Supplier supplier = modelMapper.map(supplierResponse, Supplier.class);
        return supplier;
    }


    //CRUD


    public void register(SupplierRequest supplierRequest) {
        Supplier supplier = convertDTOtoEntity(supplierRequest);
        supplierRepository.save(supplier);
    }

    public void update(int id, Map<Object, Object> fields) throws SupplierNotFoundException {
        SupplierResponse existingSupplier = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(SupplierResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingSupplier, value);
        });
        Supplier supplier = convertDTOtoEntity(existingSupplier);
        supplierRepository.save(supplier);
    }

    public void delete(int id) {
        supplierRepository.deleteById(id);
    }


    //Informational Queries
    //List All the Categories in the database
    public List<SupplierResponse> findAll(){
        List<Supplier> suppliers = supplierRepository.findAll();
        return convertEntitytoDTO(suppliers);
    }

    public SupplierResponse findById(int id) throws SupplierNotFoundException {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() ->
                new SupplierNotFoundException("Supplier with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(supplier);
    }
    public List<SupplierResponse> findByName(String name) {
        List<Supplier> suppliers = supplierRepository.findByName(name);
        return convertEntitytoDTO(suppliers);
    }
}
