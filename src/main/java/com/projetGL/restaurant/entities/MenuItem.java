package com.projetGL.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Item")
public class MenuItem {

    @Id
    private UUID itemId;

    @Column(unique=true)
    @Indexed
    private String name;
    @Indexed
    private Boolean isAvailable;
    private String desc;
    @Indexed
    private double unitCost;
    private Order order;

    @Transient
    private List<Ingredient> ingredients;

}
