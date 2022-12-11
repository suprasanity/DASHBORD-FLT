package com.example.dashboard.DAO;

import com.example.dashboard.model.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository

public class AccountRepository {

    @PersistenceContext
    private EntityManager em;

    public Account findOneByUsername(String username){
        return em.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public void save(Account user) {
        em.persist(user);
    }
}