package com.projetGL.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Facture {

    @Id
    private UUID factureId;
    private String accountId;
    private LocalDateTime postDateTime;
    private LocalDateTime paymentDate;
    private List<Order> orders;
    private PaymentMethod paymentMethod;
    private double totalCost;

    public void addOrder(Order order) {
        orders.add(order);
        order.setFacture(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setFacture(null);
    }

    public void setPaymentMethod(PaymentMethod newPaymentMethod) {
        if (paymentMethod != newPaymentMethod) {
            PaymentMethod old = paymentMethod;
            paymentMethod = newPaymentMethod;
            if (newPaymentMethod != null)
                newPaymentMethod.addFacture(this);
            if (old != null)
                old.removeFacture(this);
        }
    }
}
