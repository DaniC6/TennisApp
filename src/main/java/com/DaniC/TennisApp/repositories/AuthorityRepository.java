package com.DaniC.TennisApp.repositories;

import com.DaniC.TennisApp.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Byte> {

    Authority findByDefaultAuthorityTrue();

    boolean existsByAuthorityName(String authorityName);

    long countByDefaultAuthorityTrue();

    Set<Authority> findByVisibleTrueAndAuthorityNameIn(Set<String> authorities);
    //Esempio:  SELECT * FROM authority WHERE visible = true ANDauthority_name IN('ROLE_MODERATOR', 'ROLE_WRITER');


}
