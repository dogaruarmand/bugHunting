package com.endava.bugHunting.bug_hunting.repository2;

import com.endava.bugHunting.bug_hunting.entities.Location;
import com.endava.bugHunting.bug_hunting.entities2.Location2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Location2Repository  extends JpaRepository<Location2, Integer> {
    public Location2 findLocationByName(String name);
}
