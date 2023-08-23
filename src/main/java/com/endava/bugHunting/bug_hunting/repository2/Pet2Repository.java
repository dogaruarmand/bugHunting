package com.endava.bugHunting.bug_hunting.repository2;

import com.endava.bugHunting.bug_hunting.entities.Pet;
import com.endava.bugHunting.bug_hunting.entities2.Pet2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Pet2Repository extends JpaRepository<Pet2, Integer> {

    @Query(value = """
            select pets2.* 
            from 
                pets2, 
                users2
            where 
                pets2.user_id = users2.user_id 
                and users2.role = 'ADMIN'
                or users2.user_id = ?1 
            """, nativeQuery = true)
    public List<Pet2> findAllByUser(Integer userId);

}
