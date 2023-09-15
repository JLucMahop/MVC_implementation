package com.projetGL.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Order")
public class Order {

    @Id
    private UUID orderId;
    private LocalDateTime orderedAt;
    @Indexed
    private int noTable;
    private OrderStatus orderStatus;
    private List<MenuItem> menuItems;
    private Facture facture;

    public double getCost() {
        double totalCost = 0;
        for (MenuItem menuItem : menuItems) {
            totalCost += menuItem.getUnitCost();
        }
        return totalCost;
    }

    public void setFacture(Facture newfacture) {
        if (facture != newfacture) {
            Facture oldFacture = facture;
            facture = newfacture;
            if (newfacture != null)
                newfacture.addOrder(this);
            if (oldFacture != null)
                oldFacture.removeOrder(this);
        }
    }


}
