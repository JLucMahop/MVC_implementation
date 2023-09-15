package com.projetGL.restaurant.controllers;

import com.projetGL.restaurant.entities.Order;
import com.projetGL.restaurant.entities.OrderStatus;
import com.projetGL.restaurant.controllers.services.OrderNotFoundException;
import com.projetGL.restaurant.controllers.services.OrderService;
import com.projetGL.restaurant.controllers.services.MenuItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leCafe/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/")
    public ResponseEntity<Order> addOrder(@RequestBody @Valid Order Order) {
        return ResponseEntity.ok(orderService.addOrder(Order));
    }

    @PostMapping(value = "/delete/{OrderId}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable String OrderId) {
        return ResponseEntity.ok(orderService.deleteOrder(OrderId));
    }

    @PostMapping(value = "/modify/")
    public ResponseEntity<Order> modifyOrder(@RequestBody @Valid Order Order) throws MenuItemNotFoundException, OrderNotFoundException {
        return ResponseEntity.ok(orderService.modifyOrder(Order));
    }

    @GetMapping(value = "/noTable/{noTable}")
    public ResponseEntity<List<Order>> getAllOrdersByCost(@PathVariable int noTable) {
        return ResponseEntity.ok(orderService.findAllOrdersByNoTable(noTable));
    }

    @GetMapping(value = "/orderStatus/{status}")
    public ResponseEntity<List<Order>> getAllOrdersByOrderStatus(@PathVariable OrderStatus status) {
        return ResponseEntity.ok(orderService.findAllByOrderStatus(status));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity<?> onOrderNotFound(){
        return ResponseEntity.notFound().build();
    }
}
