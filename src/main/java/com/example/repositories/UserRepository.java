package com.example.repositories;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by saeki on 2016/07/14.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
