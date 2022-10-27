package capgemini.service;

import capgemini.dto.OrderItemRequest;
import capgemini.dto.OrderRequest;
import capgemini.dto.OrderResponse;
import capgemini.model.Order;
import capgemini.model.OrderItem;
import capgemini.persistence.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private OrderResponse convertEntitytoDTO(Order order){
        OrderResponse productResponse = modelMapper.map(order, OrderResponse.class);
        return productResponse;
    }

    private List<OrderResponse> convertEntitytoDTO(List<Order> orders){
        return orders.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private Order convertDTOtoEntity(OrderRequest orderRequest){
        Order order = modelMapper.map(orderRequest, Order.class);
        return order;
    }

    private Order convertDTOtoEntity(OrderResponse orderResponse){
        Order order = modelMapper.map(orderResponse, Order.class);
        return order;
    }


    //CRUD

    public void register(OrderRequest orderRequest) {
        Order order = convertDTOtoEntity(orderRequest);

        List<OrderItem> orderedItems = order.getListOrderItems();

        double orderTotal = 0.0;
        for(OrderItem orderedItem : orderedItems){
            double itemTotal = 0.0;
            if(orderedItem.getQuantity() > 0){
                itemTotal = orderedItem.getPrice() * orderedItem.getQuantity();
                orderedItem.setItemTotal(itemTotal);
            }
            orderTotal = orderTotal + itemTotal;
        }

        order.setTotalValue(orderTotal + order.getDeliveryCharge());
        orderRepository.save(order);
    }


    //Informational Queries
    public List<OrderResponse> findAll(){
        List<Order> orders = orderRepository.findAll();
        return convertEntitytoDTO(orders);
    }



}
