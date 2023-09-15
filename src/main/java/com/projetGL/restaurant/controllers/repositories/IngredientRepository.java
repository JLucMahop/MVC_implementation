package com.projetGL.restaurant.controllers.repositories;

import com.projetGL.restaurant.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, UUID> {

    List<Ingredient> findAllByOrderByNameAsc();

    List<Ingredient> findByIsAvailable(Boolean isAvailable);

    List<Ingredient> findByUnitCost(Double unitCost);

    List<Ingredient> findByName(String name);
}
