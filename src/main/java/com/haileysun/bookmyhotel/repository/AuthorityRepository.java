package com.haileysun.bookmyhotel.repository;

import com.haileysun.bookmyhotel.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
    // the entity class (Authority) and the type of the entity's primary key (String)
}
