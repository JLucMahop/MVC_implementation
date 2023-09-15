package com.projetGL.restaurant.controllers;

import com.projetGL.restaurant.entities.MenuItem;
import com.projetGL.restaurant.controllers.services.MenuItemNotFoundException;
import com.projetGL.restaurant.controllers.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leCafe/item")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping(value = "/")
    public ResponseEntity<MenuItem> addItem(@RequestBody @Valid MenuItem menuItem) {
        return ResponseEntity.ok(menuItemService.addItem(menuItem));
    }

    @PostMapping(value = "/delete/{itemId}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable String itemId) {
        return ResponseEntity.ok(menuItemService.deleteItem(itemId));
    }

    @PostMapping(value = "/modify/")
    public ResponseEntity<MenuItem> modifyItem(@RequestBody @Valid MenuItem menuItem) throws MenuItemNotFoundException {
        return ResponseEntity.ok(menuItemService.modifyItem(menuItem));
    }

    @GetMapping(value = "/cost/{cost}")
    public ResponseEntity<List<MenuItem>> getAllItemsByCost(@PathVariable Double cost) {
        return ResponseEntity.ok(menuItemService.findAllByCost(cost));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<MenuItem>> getAllItemsByName(@PathVariable String name) {
        return ResponseEntity.ok(menuItemService.findAllItemsByName(name));
    }

    @GetMapping(value = "/availability/{isAvailable}")
    public ResponseEntity<List<MenuItem>> getAllItemsByAvailability(@PathVariable Boolean isAvailable) {
        return ResponseEntity.ok(menuItemService.findAllByAvailability(isAvailable));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<MenuItem>> getAllItems() {
        return ResponseEntity.ok(menuItemService.findAllItems());
    }


    @ExceptionHandler(value = MenuItemNotFoundException.class)
    public ResponseEntity<?> onMenuItemNotFound(){
        return ResponseEntity.notFound().build();
    }
}
