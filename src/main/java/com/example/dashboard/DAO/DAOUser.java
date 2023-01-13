package com.example.dashboard.DAO;

import com.example.dashboard.model.WebService;
import com.example.dashboard.model.user;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.net.HttpURLConnection;
import java.net.URL;
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
        return (user) em.createQuery("SELECT u FROM user u WHERE u.username = :login AND u.password = :password").setParameter("login", login).setParameter("password", password).getSingleResult();
    }
}

