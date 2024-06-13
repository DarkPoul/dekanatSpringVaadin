package com.dekanat.ntu.dekanat.repository;


import com.dekanat.ntu.dekanat.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    // перевірка чи є користувач з таким логіном та паролем
    Optional<UserModel> findByLoginAndPassword(String login, String password);
}
