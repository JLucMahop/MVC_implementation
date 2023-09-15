package com.projetGL.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Menu {
    private String name;
    private Boolean isAvailable;
    private String desc;
}