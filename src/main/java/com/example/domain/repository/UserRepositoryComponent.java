package com.example.domain.repository;

import com.example.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by saeki on 2016/07/15.
 */
@Component
public class UserRepositoryComponent {
    @PersistenceContext
    private EntityManager entityManager;

    /* list7-14 */
    @Value("${userlist.pagesize}")
    private int PAGE_SIZE = 3;

    @Autowired
    private UserRepository repository;
    /* ******* */

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

    public Page<User> getUserInPage(Integer pageNumber){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
        return repository.findAll(pageRequest);
    }
}
