package com.projetGL.restaurant.controllers.services;

import com.projetGL.restaurant.entities.MenuHeading;
import com.projetGL.restaurant.controllers.repositories.MenuHeadingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Component
public class MenuHeadingService {
    @Autowired
    private MenuHeadingRepository menuHeadingRepository;

    public MenuHeading addMenuHeading(MenuHeading menuHeadingRequest) {
        MenuHeading menuHeading = MenuHeading.builder()
                .headingId(UUID.randomUUID())
                .desc(menuHeadingRequest.getDesc())
                .name(menuHeadingRequest.getName())
                .menus(menuHeadingRequest.getMenus())
                .MenuItem(menuHeadingRequest.getMenuItem())
                .build();

        return menuHeadingRepository.save(menuHeading);
    }

    public Boolean deleteMenuHeading(String id) {
        try {
            Optional<MenuHeading> MenuHeading = menuHeadingRepository.findById(UUID.fromString(id));
            if (MenuHeading.isPresent()){
                menuHeadingRepository.delete(MenuHeading.get());
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }

    }

    public MenuHeading modifyMenuHeading(MenuHeading MenuHeading) throws MenuHeadingNotFoundException {
        Optional<MenuHeading> MenuHeading1 = menuHeadingRepository.findById(MenuHeading.getHeadingId());
        if (MenuHeading1.isPresent())
            return menuHeadingRepository.save(MenuHeading1.get());
        else
            throw new MenuHeadingNotFoundException();
    }

    public List<MenuHeading> findAllMenuHeadings() {
        return menuHeadingRepository.findAllByOrderByNameAsc();
    }

    public List<MenuHeading> findAllMenuHeadingsByName(String name) {
        log.info(name);
        return menuHeadingRepository.findByName(name);
    }
    
}
