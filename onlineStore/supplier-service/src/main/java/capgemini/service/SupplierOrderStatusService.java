package capgemini.service;

import capgemini.dto.SupplierOrderStatusRequest;
import capgemini.dto.SupplierOrderStatusResponse;
import capgemini.exception.SupplierOrderStatusNotFoundException;
import capgemini.model.SupplierOrderStatus;
import capgemini.persistence.SupplierOrderStatusRepository;
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
public class SupplierOrderStatusService {

    @Autowired
    private SupplierOrderStatusRepository supplierOrderStatusRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private SupplierOrderStatusResponse convertEntitytoDTO(SupplierOrderStatus supplierOrderStatus){
        SupplierOrderStatusResponse supplierOrderStatusResponse = modelMapper.map(supplierOrderStatus, SupplierOrderStatusResponse.class);
        return supplierOrderStatusResponse;
    }

    private List<SupplierOrderStatusResponse> convertEntitytoDTO(List<SupplierOrderStatus> supplierOrderStatuses){
        return supplierOrderStatuses.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private SupplierOrderStatus convertDTOtoEntity(SupplierOrderStatusRequest supplierOrderStatusRequest){
        SupplierOrderStatus supplierOrderStatus = modelMapper.map(supplierOrderStatusRequest, SupplierOrderStatus.class);
        return supplierOrderStatus;
    }

    private SupplierOrderStatus convertDTOtoEntity(SupplierOrderStatusResponse supplierOrderStatusResponse){
        SupplierOrderStatus supplierOrderStatus = modelMapper.map(supplierOrderStatusResponse, SupplierOrderStatus.class);
        return supplierOrderStatus;
    }


    //CRUD


    public void register(SupplierOrderStatusRequest supplierOrderStatusRequest) {
        SupplierOrderStatus supplierOrderStatus = convertDTOtoEntity(supplierOrderStatusRequest);
        supplierOrderStatusRepository.save(supplierOrderStatus);
    }

    public void update(int id, Map<Object, Object> fields) throws SupplierOrderStatusNotFoundException {
        SupplierOrderStatusResponse existingSupplierOrderStatus = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(SupplierOrderStatusResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingSupplierOrderStatus, value);
        });
        SupplierOrderStatus supplierOrderStatus = convertDTOtoEntity(existingSupplierOrderStatus);
        supplierOrderStatusRepository.save(supplierOrderStatus);
    }

    public void delete(int id) {
        supplierOrderStatusRepository.deleteById(id);
    }


    //Informational Queries
    //List All the Categories in the database
    public List<SupplierOrderStatusResponse> findAll(){
        List<SupplierOrderStatus> supplierOrderStatuses = supplierOrderStatusRepository.findAll();
        return convertEntitytoDTO(supplierOrderStatuses);
    }

    public SupplierOrderStatusResponse findById(int id) throws SupplierOrderStatusNotFoundException {
        SupplierOrderStatus supplierOrderStatus = supplierOrderStatusRepository.findById(id).orElseThrow(() ->
                new SupplierOrderStatusNotFoundException("SupplierOrderStatus with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(supplierOrderStatus);
    }
    public List<SupplierOrderStatusResponse> findByName(String name) {
        List<SupplierOrderStatus> supplierOrderStatuses = supplierOrderStatusRepository.findByOrderStatusName(name);
        return convertEntitytoDTO(supplierOrderStatuses);
    }
}
