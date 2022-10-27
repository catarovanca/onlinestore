package capgemini.controller;

import capgemini.dto.OrderRequest;
import capgemini.dto.OrderResponse;
import capgemini.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void register(@RequestBody OrderRequest orderRequest){
        orderService.register(orderRequest);
    }

    @GetMapping
    public List<OrderResponse> findAll() {
        return orderService.findAll();
    }
}
