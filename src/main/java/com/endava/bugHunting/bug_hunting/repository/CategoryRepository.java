package com.endava.bugHunting.bug_hunting.repository;

import com.endava.bugHunting.bug_hunting.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value="select * from categories where lower(category) = lower(?1)", nativeQuery = true)
    Category findCategoryByCatgoryLowerCase(String category);

    Category findCategoryByCategory(String category);
}
