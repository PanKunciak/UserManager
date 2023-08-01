package com.HealthGalactica.UserMenagment.Repository;

import com.HealthGalactica.UserMenagment.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByEmailAndPassword(String email, String password);
}
