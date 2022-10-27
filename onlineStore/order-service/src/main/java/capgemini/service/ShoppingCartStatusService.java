package capgemini.service;

import capgemini.dto.ShoppingCartStatusRequest;
import capgemini.dto.ShoppingCartStatusResponse;
import capgemini.model.ShoppingCartStatus;
import capgemini.persistence.ShoppingCartStatusRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartStatusService {

    @Autowired
    private ShoppingCartStatusRepository shoppingCartStatusRepository;


    @Autowired
    private ModelMapper modelMapper;

    //Mappers
    private ShoppingCartStatusResponse convertEntitytoDTO(ShoppingCartStatus shoppingCartStatus){
        ShoppingCartStatusResponse shoppingCartStatusResponse = modelMapper.map(shoppingCartStatus, ShoppingCartStatusResponse.class);
        return shoppingCartStatusResponse;
    }

    private List<ShoppingCartStatusResponse> convertEntitytoDTO(List<ShoppingCartStatus> shoppingCartStatuses){
        return shoppingCartStatuses.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private ShoppingCartStatus convertDTOtoEntity(ShoppingCartStatusRequest shoppingCartStatusRequest){
        ShoppingCartStatus shoppingCartStatus = modelMapper.map(shoppingCartStatusRequest, ShoppingCartStatus.class);
        return shoppingCartStatus;
    }

    private ShoppingCartStatus convertDTOtoEntity(ShoppingCartStatusResponse shoppingCartStatusResponse){
        ShoppingCartStatus shoppingCartStatus = modelMapper.map(shoppingCartStatusResponse, ShoppingCartStatus.class);
        return shoppingCartStatus;
    }

    //CRUD

    public void register(ShoppingCartStatusRequest shoppingCartStatusRequest) {
        ShoppingCartStatus shoppingCartStatus = convertDTOtoEntity(shoppingCartStatusRequest);
        shoppingCartStatusRepository.save(shoppingCartStatus);
    }

    public List<ShoppingCartStatusResponse> findAll(){
        List<ShoppingCartStatus> shoppingCartStatuses = shoppingCartStatusRepository.findAll();
        return convertEntitytoDTO(shoppingCartStatuses);
    }
}
