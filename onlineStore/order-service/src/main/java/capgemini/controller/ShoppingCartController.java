package capgemini.controller;

import capgemini.dto.ShoppingCartRequest;
import capgemini.dto.ShoppingCartResponse;
import capgemini.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping
    public void register(@RequestBody ShoppingCartRequest shoppingCartRequest) {
        shoppingCartService.register(shoppingCartRequest);
    }

    @GetMapping
    public List<ShoppingCartResponse> findAll() {
        return shoppingCartService.findAll();
    }
}