package com.projetGL.restaurant.controllers;

import com.projetGL.restaurant.entities.Ingredient;
import com.projetGL.restaurant.controllers.services.IngredientNotFoundException;
import com.projetGL.restaurant.controllers.services.IngrédientService;
import com.projetGL.restaurant.controllers.services.MenuItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leCafe/ingredient")
public class IngredientController {

    @Autowired
    private IngrédientService ingrédientService;

    @PostMapping(value = "/")
    public ResponseEntity<Ingredient> addIgredient(@RequestBody @Valid Ingredient ingredient) {
        return ResponseEntity.ok(ingrédientService.addIgredient(ingredient));
    }

    @PostMapping(value = "/delete/{ingredientId}")
    public ResponseEntity<Boolean> deleteIngredient(@PathVariable String ingredientId) {
        return ResponseEntity.ok(ingrédientService.deleteIngredient(ingredientId));
    }

    @PostMapping(value = "/modify/")
    public ResponseEntity<Ingredient> modifyIngredient(@RequestBody @Valid Ingredient ingredient) throws MenuItemNotFoundException, IngredientNotFoundException {
        return ResponseEntity.ok(ingrédientService.modifyIngredient(ingredient));
    }

    @GetMapping(value = "/cost/{cost}")
    public ResponseEntity<List<Ingredient>> getAllIngredientsByCost(@PathVariable Double cost) {
        return ResponseEntity.ok(ingrédientService.findAllByCost(cost));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<Ingredient>> getAllIngredientsByName(@PathVariable String name) {
        return ResponseEntity.ok(ingrédientService.findAllIngredientsByName(name));
    }

    @GetMapping(value = "/availability/{isAvailable}")
    public ResponseEntity<List<Ingredient>> getAllIngredientsByAvailability(@PathVariable Boolean isAvailable) {
        return ResponseEntity.ok(ingrédientService.findAllByAvailability(isAvailable));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ResponseEntity.ok(ingrédientService.findAllIngredients());
    }

    @ExceptionHandler(value = IngredientNotFoundException.class)
    public ResponseEntity<?> onIngredientNotFound(){
        return ResponseEntity.notFound().build();
    }
}
