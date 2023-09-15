package com.projetGL.restaurant.controllers.services;

import com.projetGL.restaurant.entities.Ingredient;
import com.projetGL.restaurant.controllers.repositories.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class IngrédientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient addIgredient(Ingredient ingredientRequest) {
        Ingredient ingredient = Ingredient.builder()
                .ingredientId(UUID.randomUUID())
                .desc(ingredientRequest.getDesc())
                .quantity(ingredientRequest.getQuantity())
                .isAvailable(ingredientRequest.getIsAvailable())
                .name(ingredientRequest.getName())
                .unitCost(ingredientRequest.getUnitCost())
                .build();

        return ingredientRepository.save(ingredient);
    }

    public Boolean deleteIngredient(String id) {
        try {
            Optional<Ingredient> ingredient = ingredientRepository.findById(UUID.fromString(id));
            if (ingredient.isPresent()){
                ingredientRepository.delete(ingredient.get());
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }

    }

    public Ingredient modifyIngredient(Ingredient ingredient) throws IngredientNotFoundException {
        Optional<Ingredient> ingredient1 = ingredientRepository.findById(ingredient.getIngredientId());
        if (ingredient1.isPresent())
            return ingredientRepository.save(ingredient1.get());
        else
            throw new IngredientNotFoundException();
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAllByOrderByNameAsc();
    }

    public List<Ingredient> findAllByCost(double cost) {
        return ingredientRepository.findByUnitCost(cost);
    }

    public List<Ingredient> findAllByAvailability(boolean isAvailable) {
        return ingredientRepository.findByIsAvailable(isAvailable);
    }

    public List<Ingredient> findAllIngredientsByName(String name) {
        log.info(name);
        return ingredientRepository.findByName(name);
    }
}
