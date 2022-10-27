package capgemini.service;

import capgemini.dto.OrderStatusRequest;
import capgemini.dto.OrderStatusResponse;
import capgemini.model.OrderStatus;
import capgemini.persistence.OrderRepository;
import capgemini.persistence.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;


    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private OrderStatusResponse convertEntitytoDTO(OrderStatus orderStatus){
        OrderStatusResponse orderStatusResponse = modelMapper.map(orderStatus, OrderStatusResponse.class);
        return orderStatusResponse;
    }

    private List<OrderStatusResponse> convertEntitytoDTO(List<OrderStatus> orderStatusList){
        return orderStatusList.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private OrderStatus convertDTOtoEntity(OrderStatusRequest orderStatusRequest){
        OrderStatus orderStatus = modelMapper.map(orderStatusRequest, OrderStatus.class);
        return orderStatus;
    }

    private OrderStatus convertDTOtoEntity(OrderStatusResponse orderStatusResponse){
        OrderStatus orderStatus = modelMapper.map(orderStatusResponse, OrderStatus.class);
        return orderStatus;
    }

    //CRUD

    public void register(OrderStatusRequest orderStatusRequest) {
        OrderStatus orderStatus = convertDTOtoEntity(orderStatusRequest);
        orderStatusRepository.save(orderStatus);
    }

    public List<OrderStatusResponse> findAll(){
        List<OrderStatus> orderStatusList = orderStatusRepository.findAll();
        return convertEntitytoDTO(orderStatusList);
    }
}
