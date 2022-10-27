package capgemini.service;


import capgemini.dto.ShoppingCartRequest;
import capgemini.dto.ShoppingCartResponse;
import capgemini.model.OrderItem;
import capgemini.model.ShoppingCart;
import capgemini.persistence.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ModelMapper modelMapper;


    //Mappers
    private ShoppingCartResponse convertEntitytoDTO(ShoppingCart shoppingCart){
        ShoppingCartResponse shoppingCartResponse = modelMapper.map(shoppingCart, ShoppingCartResponse.class);
        return shoppingCartResponse;
    }

    private List<ShoppingCartResponse> convertEntitytoDTO(List<ShoppingCart> orders){
        return orders.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private ShoppingCart convertDTOtoEntity(ShoppingCartRequest shoppingCartRequest){
        ShoppingCart shoppingCart = modelMapper.map(shoppingCartRequest, ShoppingCart.class);
        return shoppingCart;
    }

    private ShoppingCart convertDTOtoEntity(ShoppingCartResponse shoppingCartResponse){
        ShoppingCart shoppingCart = modelMapper.map(shoppingCartResponse, ShoppingCart.class);
        return shoppingCart;
    }


    //CRUD

    public void register(ShoppingCartRequest shoppingCartRequest) {
        ShoppingCart shoppingCart = convertDTOtoEntity(shoppingCartRequest);

        List<OrderItem> orderedItems = shoppingCart.getListOrderItems();

        double orderTotal = 0.0;
        for(OrderItem orderedItem : orderedItems){
            double itemTotal = 0.0;
            if(orderedItem.getQuantity() > 0){
                itemTotal = orderedItem.getPrice() * orderedItem.getQuantity();
                orderedItem.setItemTotal(itemTotal);
            }
            orderTotal = orderTotal + itemTotal;
        }

        shoppingCart.setTotalValue(orderTotal + shoppingCart.getDeliveryCharge());
        shoppingCartRepository.save(shoppingCart);
    }


    //Informational Queries
    public List<ShoppingCartResponse> findAll(){
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        return convertEntitytoDTO(shoppingCarts);
    }
}
