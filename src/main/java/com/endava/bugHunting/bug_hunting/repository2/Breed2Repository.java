package com.endava.bugHunting.bug_hunting.repository2;

import com.endava.bugHunting.bug_hunting.entities.Breed;
import com.endava.bugHunting.bug_hunting.entities2.Breed2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Breed2Repository extends JpaRepository<Breed2, Long> {

    public Breed2 findBreedByBreed(String breed);

}
