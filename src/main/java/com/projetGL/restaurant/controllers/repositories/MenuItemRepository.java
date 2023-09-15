package com.projetGL.restaurant.controllers.repositories;

import com.projetGL.restaurant.entities.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, UUID> {

    List<MenuItem> findAllByOrderByNameAsc();

    List<MenuItem> findAllByIsAvailableOrderByNameAsc(Boolean isAvailable);

    List<MenuItem> findAllByUnitCostOrderByNameAsc(Double unitCost);


    List<MenuItem> findAllByNameOrderByNameAsc(String name);
}
