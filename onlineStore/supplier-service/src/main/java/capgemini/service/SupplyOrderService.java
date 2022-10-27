package capgemini.service;

import capgemini.dto.SupplyOrderRequest;
import capgemini.dto.SupplyOrderResponse;
import capgemini.exception.SupplyOrderNotFoundException;
import capgemini.model.SupplyOrder;
import capgemini.persistence.SupplyOrderRepository;
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
public class SupplyOrderService {

    @Autowired
    private SupplyOrderRepository supplyOrdersRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private SupplyOrderResponse convertEntitytoDTO(SupplyOrder supplyOrder){
        SupplyOrderResponse supplyOrderResponse = modelMapper.map(supplyOrder, SupplyOrderResponse.class);
        return supplyOrderResponse;
    }

    private List<SupplyOrderResponse> convertEntitytoDTO(List<SupplyOrder> supplyOrders){
        return supplyOrders.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private SupplyOrder convertDTOtoEntity(SupplyOrderRequest supplyOrderRequest){
        SupplyOrder supplyOrder = modelMapper.map(supplyOrderRequest, SupplyOrder.class);
        return supplyOrder;
    }

    private SupplyOrder convertDTOtoEntity(SupplyOrderResponse supplyOrderResponse){
        SupplyOrder supplyOrder = modelMapper.map(supplyOrderResponse, SupplyOrder.class);
        return supplyOrder;
    }


    //CRUD


    public void register(SupplyOrderRequest supplyOrderRequest) {
        SupplyOrder supplyOrder = convertDTOtoEntity(supplyOrderRequest);
        supplyOrdersRepository.save(supplyOrder);
    }

    public void update(int id, Map<Object, Object> fields) throws SupplyOrderNotFoundException {
        SupplyOrderResponse existingSupplyOrder = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(SupplyOrderResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingSupplyOrder, value);
        });
        SupplyOrder supplyOrder = convertDTOtoEntity(existingSupplyOrder);
        supplyOrdersRepository.save(supplyOrder);
    }

    public void delete(int id) {
        supplyOrdersRepository.deleteById(id);
    }


    //Informational Queries
    //List All the Categories in the database
    public List<SupplyOrderResponse> findAll(){
        List<SupplyOrder> supplyOrders = supplyOrdersRepository.findAll();
        return convertEntitytoDTO(supplyOrders);
    }

    public SupplyOrderResponse findById(int id) throws SupplyOrderNotFoundException {
        SupplyOrder supplyOrder = supplyOrdersRepository.findById(id).orElseThrow(() ->
                new SupplyOrderNotFoundException("SupplyOrder with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(supplyOrder);
    }
    public List<SupplyOrderResponse> findByName(String name) {
        List<SupplyOrder> supplyOrders = supplyOrdersRepository.findByName(name);
        return convertEntitytoDTO(supplyOrders);
    }
}