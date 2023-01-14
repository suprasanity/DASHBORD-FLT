package com.example.dashboard.DAO;

import com.example.dashboard.model.WebService;
import com.example.dashboard.model.user;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DAOUser  {
    @PersistenceContext
    private EntityManager em;



    @Transactional
    public void save(String login, String password, String email, String tel) {
        user user = new user();
        user.setUsername(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setTel(tel);
        em.persist(user);
    }
    @Transactional
    public user getUserByLogin(String login, String password) {
        List<user> users = em.createQuery("select u from user u where u.username = :login and u.password = :password", user.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
}

