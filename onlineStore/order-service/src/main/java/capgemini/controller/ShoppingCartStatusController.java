package capgemini.controller;

import capgemini.dto.OrderStatusRequest;
import capgemini.dto.ShoppingCartStatusRequest;
import capgemini.dto.ShoppingCartStatusResponse;
import capgemini.service.ShoppingCartStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shoppingcartstatus")
public class ShoppingCartStatusController {

    @Autowired
    private ShoppingCartStatusService shoppingCartStatusService;

    @PostMapping
    public void register(@RequestBody ShoppingCartStatusRequest shoppingCartStatusRequest){
        shoppingCartStatusService.register(shoppingCartStatusRequest);
    }

    @GetMapping
    public List<ShoppingCartStatusResponse> findAll() {
        return shoppingCartStatusService.findAll();
    }
}
