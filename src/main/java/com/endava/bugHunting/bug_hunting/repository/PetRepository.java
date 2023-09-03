package com.endava.bugHunting.bug_hunting.repository;

import com.endava.bugHunting.bug_hunting.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

//    @Query(value = """
//            select pets.*
//            from
//                pets,
//                users
//            where
//                pets.user_id = users.user_id
//                and users.role = 'ADMIN'
//                or users.user_id = ?1
//            order by
//                pets.id desc
//            """, nativeQuery = true)
//    public List<Pet> findAllByUser(Integer userId);

    @Query(value = """
            select pets.* 
            from 
                pets, 
                users,
                adoptions
            where 
                pets.id = adoptions.pet_id
                and users.user_id = adoptions.user_id 
                and users.user_id = ?1
            order by 
                pets.id desc 
            """, nativeQuery = true)
    public List<Pet> findAllByUser(Integer userId);

}
