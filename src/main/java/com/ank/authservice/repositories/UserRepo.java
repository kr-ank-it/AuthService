package com.ank.authservice.repositories;

import com.ank.authservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmailEquals(String email);

    @Override
    User save(User user);
}
