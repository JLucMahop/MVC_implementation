package com.projetGL.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;
import java.util.UUID;

/**
 * Dans cette classe, on a une relation contenu~contenant.
 * Contenant : MenuHeading qui represente les rubriques
 * Contenu : Menu ( qui dépendent fortement de la rubrique.
 *
 * Dans ce type de relation, si on supprime le contenant alors on supprime aussi les contenus.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Rubrique")
public class MenuHeading {

    @Id
    private UUID headingId;
    @Indexed
    private String name;
    private String desc;
    private List<Menu> menus;
    private List<MenuItem> MenuItem;


}
