package com.projetGL.restaurant.controllers.services;

import com.projetGL.restaurant.entities.MenuItem;
import com.projetGL.restaurant.controllers.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItem addItem(MenuItem itemRequest) {
        MenuItem menuItem = MenuItem.builder()
                .itemId(UUID.randomUUID())
                .desc(itemRequest.getDesc())
                .ingredients(itemRequest.getIngredients())
                .isAvailable(itemRequest.getIsAvailable())
                .name(itemRequest.getName())
                .unitCost(itemRequest.getUnitCost())
                .build();

        return menuItemRepository.save(menuItem);
    }

    public Boolean deleteItem(String id) {
        try {
            Optional<MenuItem> menuItem = menuItemRepository.findById(UUID.fromString(id));
            if (menuItem.isPresent()){
                menuItemRepository.delete(menuItem.get());
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }

    }

    public MenuItem modifyItem(MenuItem menuItem) throws MenuItemNotFoundException {
        Optional<MenuItem> menuItem1 = menuItemRepository.findById(menuItem.getItemId());
        if (menuItem1.isPresent())
            return menuItemRepository.save(menuItem);
        else
            throw new MenuItemNotFoundException();
    }

    public List<MenuItem> findAllItems() {
        return menuItemRepository.findAllByOrderByNameAsc();
    }

    public List<MenuItem> findAllByCost(double cost) {
        return menuItemRepository.findAllByUnitCostOrderByNameAsc(cost);
    }

    public List<MenuItem> findAllByAvailability(boolean isAvailable) {
        return menuItemRepository.findAllByIsAvailableOrderByNameAsc(isAvailable);
    }

    public List<MenuItem> findAllItemsByName(String name) {
        return menuItemRepository.findAllByNameOrderByNameAsc(name);
    }
}
