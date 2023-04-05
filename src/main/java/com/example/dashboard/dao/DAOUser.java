package com.example.dashboard.dao;


import com.example.dashboard.model.Tache;
import com.example.dashboard.model.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.util.Collections.emptyList;


@Repository
public class DAOUser  {
    @PersistenceContext
    private EntityManager em;



    @Transactional
    public void save(String login, String password, String email, String tel) {
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setTel(tel);
        em.persist(user);
    }
    @Transactional
    public Long getUserByLogin(String login, String password) {
        List<User> users = em.createQuery("select u from User u where u.username = :login and u.password = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0).getId();
    }
    @Transactional
    public List<Tache> getTachesByUserId(long id) {
        List<Tache> taches = em.createQuery("select u from Tache u where u.user.userId =:id", Tache.class)
                .setParameter("id", id)
                .getResultList();
        if (taches.isEmpty()) {
            return emptyList();
        }
        return taches;
    }
}

