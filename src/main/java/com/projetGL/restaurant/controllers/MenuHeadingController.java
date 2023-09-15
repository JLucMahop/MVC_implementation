package com.projetGL.restaurant.controllers;

import com.projetGL.restaurant.entities.MenuHeading;
import com.projetGL.restaurant.controllers.services.MenuHeadingNotFoundException;
import com.projetGL.restaurant.controllers.services.MenuHeadingService;
import com.projetGL.restaurant.controllers.services.MenuItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leCafe/MenuHeading")
public class MenuHeadingController {

    @Autowired
    private MenuHeadingService MenuHeadingService;

    @PostMapping(value = "/")
    public ResponseEntity<MenuHeading> addIgredient(@RequestBody @Valid MenuHeading MenuHeading) {
        return ResponseEntity.ok(MenuHeadingService.addMenuHeading(MenuHeading));
    }

    @PostMapping(value = "/delete/{MenuHeadingId}")
    public ResponseEntity<Boolean> deleteMenuHeading(@PathVariable String MenuHeadingId) {
        return ResponseEntity.ok(MenuHeadingService.deleteMenuHeading(MenuHeadingId));
    }

    @PostMapping(value = "/modify/")
    public ResponseEntity<MenuHeading> modifyMenuHeading(@RequestBody @Valid MenuHeading MenuHeading) throws MenuItemNotFoundException, MenuHeadingNotFoundException {
        return ResponseEntity.ok(MenuHeadingService.modifyMenuHeading(MenuHeading));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<MenuHeading>> getAllMenuHeadingsByName(@PathVariable String name) {
        return ResponseEntity.ok(MenuHeadingService.findAllMenuHeadingsByName(name));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<MenuHeading>> getAllMenuHeadings() {
        return ResponseEntity.ok(MenuHeadingService.findAllMenuHeadings());
    }

    @ExceptionHandler(value = MenuHeadingNotFoundException.class)
    public ResponseEntity<?> onMenuHeadingNotFound(){
        return ResponseEntity.notFound().build();
    }
}
