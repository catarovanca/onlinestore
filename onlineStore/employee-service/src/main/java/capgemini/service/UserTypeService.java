package capgemini.service;

import capgemini.dto.UserTypeRequest;
import capgemini.dto.UserTypeResponse;
import capgemini.exception.UserTypeNotFoundException;
import capgemini.model.UserType;
import capgemini.persistence.UserTypeRepository;
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
public class UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    //Informational Queries
    //List All the UserTypes in the database
    public List<UserTypeResponse> findAll(){
        List<UserType> userType = userTypeRepository.findAll();
        return convertEntitytoDTO(userType);
    }

    public UserTypeResponse findById(int id) throws UserTypeNotFoundException {
        UserType userType = userTypeRepository.findById(id).orElseThrow(() ->
                new UserTypeNotFoundException("UserType with the id: " + id + " doesn't exist!"));;
        return convertEntitytoDTO(userType);
    }
    public List<UserTypeResponse> findByName(String name) {
        List<UserType> userTypes = userTypeRepository.findByNameContainsIgnoreCase(name);
        return convertEntitytoDTO(userTypes);
    }


    //CRUD
    public void register(UserTypeRequest userTypeRequest) {
        UserType userType = convertDTOtoEntity(userTypeRequest);
        userTypeRepository.save(userType);
    }

    public void update(int id, Map<Object, Object> fields) throws UserTypeNotFoundException {
        UserTypeResponse existingUserType = findById(id);
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(UserTypeResponse.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingUserType, value);
        });
        UserType userType = convertDTOtoEntity(existingUserType);
        userTypeRepository.save(userType);
    }

    public void delete(int id) {
        userTypeRepository.deleteById(id);
    }




    //Mappers
    private UserTypeResponse convertEntitytoDTO(UserType userType){
        UserTypeResponse userTypeResponse = modelMapper.map(userType, UserTypeResponse.class);
        return userTypeResponse;
    }

    private List<UserTypeResponse> convertEntitytoDTO(List<UserType> courier){
        return courier.stream().map(this::convertEntitytoDTO).collect(Collectors.toList());
    }

    private UserType convertDTOtoEntity(UserTypeRequest userTypeRequest){
        UserType userType = modelMapper.map(userTypeRequest, UserType.class);
        return userType;
    }

    private UserType convertDTOtoEntity(UserTypeResponse userTypeResponse){
        UserType userType = modelMapper.map(userTypeResponse, UserType.class);
        return userType;
    }


}
