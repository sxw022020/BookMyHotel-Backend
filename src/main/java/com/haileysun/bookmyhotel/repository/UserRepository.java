package com.haileysun.bookmyhotel.repository;

import com.haileysun.bookmyhotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // <User, String>:
    // name of entity class, ID type of the entity class
}
