package capgemini.service;

import capgemini.dto.SupplierEmployeeContactRequest;
import capgemini.dto.SupplierEmployeeContactResponse;
import capgemini.exception.SupplierEmployeeContactNotFoundException;
import capgemini.model.SupplierEmployeeContact;
import capgemini.persistence.SupplierEmployeeContactRepository;
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
public class SupplierEmployeeContactService {

    @Autowired
    private SupplierEmployeeContactRepository supplierEmployeeContactRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private SupplierEmployeeContactResponse convertEntitytoDTO(SupplierEmployeeContact supplierEmployeeContact){
        SupplierEmployeeContactResponse supplierEmployeeContactResponse = modelMapper.map(supplierEmployeeContact, SupplierEmployeeContactResponse.class);
        return supplierEmployeeContactResponse;
    }

    private List<SupplierEmployeeContactResponse> convertEntitytoDTO(List<SupplierEmployeeContact> supplierEmployeeContacts){
        return supplierEmployeeContacts.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private SupplierEmployeeContact convertDTOtoEntity(SupplierEmployeeContactRequest supplierEmployeeContactRequest){
        SupplierEmployeeContact supplierEmployeeContact = modelMapper.map(supplierEmployeeContactRequest, SupplierEmployeeContact.class);
        return supplierEmployeeContact;
    }

    private SupplierEmployeeContact convertDTOtoEntity(SupplierEmployeeContactResponse supplierEmployeeContactResponse){
        SupplierEmployeeContact supplierEmployeeContact = modelMapper.map(supplierEmployeeContactResponse, SupplierEmployeeContact.class);
        return supplierEmployeeContact;
    }


    //CRUD


    public void register(SupplierEmployeeContactRequest supplierEmployeeContactRequest) {
        SupplierEmployeeContact supplierEmployeeContact = convertDTOtoEntity(supplierEmployeeContactRequest);
        supplierEmployeeContactRepository.save(supplierEmployeeContact);
    }

    public void update(int id, Map<Object, Object> fields) throws SupplierEmployeeContactNotFoundException {
        SupplierEmployeeContactResponse existingSupplierEmployeeContact = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(SupplierEmployeeContactResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingSupplierEmployeeContact, value);
        });
        SupplierEmployeeContact supplierEmployeeContact = convertDTOtoEntity(existingSupplierEmployeeContact);
        supplierEmployeeContactRepository.save(supplierEmployeeContact);
    }

    public void delete(int id) {
        supplierEmployeeContactRepository.deleteById(id);
    }


    //Informational Queries
    //List All the Categories in the database
    public List<SupplierEmployeeContactResponse> findAll(){
        List<SupplierEmployeeContact> supplierEmployeeContacts = supplierEmployeeContactRepository.findAll();
        return convertEntitytoDTO(supplierEmployeeContacts);
    }

    public SupplierEmployeeContactResponse findById(int id) throws SupplierEmployeeContactNotFoundException {
        SupplierEmployeeContact supplierEmployeeContact = supplierEmployeeContactRepository.findById(id).orElseThrow(() ->
                new SupplierEmployeeContactNotFoundException("SupplierEmployeeContact with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(supplierEmployeeContact);
    }
    public List<SupplierEmployeeContactResponse> findByName(String name) {
        List<SupplierEmployeeContact> supplierEmployeeContacts = supplierEmployeeContactRepository.findByFirstNameContainsIgnoreCase(name);
        return convertEntitytoDTO(supplierEmployeeContacts);
    }
}
