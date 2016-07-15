package com.example.domain.repository;

import com.example.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by saeki on 2016/07/14.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findById(Long id);
    public Iterable<User> findByIdIsNotNullOrderByIdDesc();
    public List<User> findByNameLike(String name);
    public List<User> findByAgeGreaterThan(Integer age);
    public List<User> findByAgeBetween(Integer age1, Integer age2);
}
