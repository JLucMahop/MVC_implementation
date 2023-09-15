package com.projetGL.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Ingredient")
public class Ingredient {

    @Id
    private UUID ingredientId;
    @Indexed
    private String name;
    @Indexed
    private Boolean isAvailable;
    private String desc;
    @Indexed
    private double unitCost;
    private int quantity;

}
