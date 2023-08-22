package com.endava.bugHunting.bug_hunting.repository2;

import com.endava.bugHunting.bug_hunting.entities.Category;
import com.endava.bugHunting.bug_hunting.entities2.Category2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Category2Repository extends JpaRepository<Category2, Integer> {

    @Query(value="select * from categories2 where lower(category) = lower(?1)", nativeQuery = true)
    Category2 findCategoryByCatgoryLowerCase(String category);

    Category2 findCategoryByCategory(String category);
}
