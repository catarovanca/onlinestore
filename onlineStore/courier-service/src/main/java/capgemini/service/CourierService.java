package capgemini.service;

import capgemini.dto.CourierRequest;
import capgemini.dto.CourierResponse;
import capgemini.exception.CourierNotFoundException;
import capgemini.model.Courier;
import capgemini.persistence.CourierRepository;
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
public class CourierService {

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private ModelMapper modelMapper;


    // CRUD
    public void register(CourierRequest courierRequest) {
        Courier courier = convertDTOtoEntity(courierRequest);
        courierRepository.save(courier);
    }

    public void update(int id, Map<Object, Object> fields) throws CourierNotFoundException {
        CourierResponse existingCourier = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(CourierResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingCourier, value);
        });
        Courier courier = convertDTOtoEntity(existingCourier);
        courierRepository.save(courier);
    }

    public void delete(int id) {
        courierRepository.deleteById(id);
    }



    //Mappers
    private CourierResponse convertEntitytoDTO(Courier courier){
        CourierResponse courierResponse = modelMapper.map(courier, CourierResponse.class);
        return courierResponse;
    }

    private List<CourierResponse> convertEntitytoDTO(List<Courier> courier){
        return courier.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private Courier convertDTOtoEntity(CourierRequest courierRequest){
        Courier courier = modelMapper.map(courierRequest, Courier.class);
        return courier;
    }

    private Courier convertDTOtoEntity(CourierResponse courierResponse){
        Courier courier = modelMapper.map(courierResponse, Courier.class);
        return courier;
    }

    //Informational Queries

    //List All the Couriers in the database
    public List<CourierResponse> findAll(){
        List<Courier> couriers = courierRepository.findAll();
        return convertEntitytoDTO(couriers);
    }

    public CourierResponse findById(int id) throws CourierNotFoundException {
        Courier courier = courierRepository.findById(id).orElseThrow(() ->
                new CourierNotFoundException("Courier with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(courier);
    }
    public List<CourierResponse> findByName(String name) {
        List<Courier> category = courierRepository.findByNameContainingIgnoreCase(name);
        return convertEntitytoDTO(category);
    }
}
