package com.endava.bugHunting.bug_hunting.repository;

import com.endava.bugHunting.bug_hunting.entities.Location;
import com.endava.bugHunting.bug_hunting.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository  extends JpaRepository<Location, Integer> {
    public Location findLocationByName(String name);
}
