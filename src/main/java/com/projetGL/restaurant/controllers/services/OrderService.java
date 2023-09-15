package com.projetGL.restaurant.controllers.services;

import com.projetGL.restaurant.entities.Order;
import com.projetGL.restaurant.entities.OrderStatus;
import com.projetGL.restaurant.controllers.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository OrderRepository;

    public Order addOrder(Order orderRequest) {
        Order order = Order.builder()
                .orderId(UUID.randomUUID())
                .orderedAt(LocalDateTime.now())
                .menuItems(orderRequest.getMenuItems())
                .orderStatus(OrderStatus.ORDER)
                .noTable(orderRequest.getNoTable())
                .build();

        order.setFacture(orderRequest.getFacture());

        return OrderRepository.save(order);
    }

    public Boolean deleteOrder(String id) {
        try {
            Optional<Order> Order = OrderRepository.findById(UUID.fromString(id));
            if (Order.isPresent()){
                OrderRepository.delete(Order.get());
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }

    }

    public Order modifyOrder(Order Order) throws OrderNotFoundException {
        Optional<Order> Order1 = OrderRepository.findById(Order.getOrderId());
        if (Order1.isPresent())
            return OrderRepository.save(Order);
        else
            throw new OrderNotFoundException();
    }

    public List<Order> findAllOrders() {
        return OrderRepository.findAllByOrderByNameAsc();
    }

    public List<Order> findAllByOrderStatus(OrderStatus orderStatus) {
        return OrderRepository.findByOrderStatus(orderStatus);
    }

    public List<Order> findAllOrdersByNoTable(int noTable) {
        return OrderRepository.findByNoTable(noTable);
    }
}
