package com.example.domain.repository;

import com.example.domain.model.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by saeki on 2016/07/15.
 */
@Service
public class UserRepositoryComponent {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll() {
        return (List<User>) entityManager
                .createQuery("from User")
                .getResultList();
    }

    public User get(int num) {
        return (User) entityManager
                .createQuery("from User where id = " + num)
                .getSingleResult();
    }

    public List<User> find(String fstr) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);

        Root<User> root = query.from(User.class);
        query.select(root).where(builder.like(root.get("name"), fstr));

        return (List<User>) entityManager
                .createQuery(query)
                .getResultList();
    }
}
