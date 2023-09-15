package com.projetGL.restaurant.controllers.repositories;

import com.projetGL.restaurant.entities.MenuHeading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface MenuHeadingRepository extends CrudRepository<MenuHeading, UUID> {

    List<MenuHeading> findAllByOrderByNameAsc();

    List<MenuHeading> findByName(String name);
}
