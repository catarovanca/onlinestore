package capgemini.controller;

import capgemini.dto.OrderRequest;
import capgemini.dto.OrderResponse;
import capgemini.dto.OrderStatusRequest;
import capgemini.dto.OrderStatusResponse;
import capgemini.model.OrderStatus;
import capgemini.service.OrderService;
import capgemini.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderstatus")
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    @PostMapping
    public void register(@RequestBody OrderStatusRequest orderStatusRequest){
        orderStatusService.register(orderStatusRequest);
    }

    @GetMapping
    public List<OrderStatusResponse> findAll() {
        return orderStatusService.findAll();
    }
}
