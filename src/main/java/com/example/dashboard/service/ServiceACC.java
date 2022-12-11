package com.example.dashboard.service;

import com.example.dashboard.DAO.AccountRepository;
import com.example.dashboard.DAO.DAOWSStatus;
import com.example.dashboard.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class ServiceACC {
    @Autowired
    private AccountRepository accountRepository;

    public Account getAcc(String username){
        return accountRepository.findOneByUsername(username);
    }
    @PostConstruct
    public void initialize(){
        if(accountRepository.findOneByUsername("kamino") == null){
            save(new Account("kamino", "kamino","slave"));
        }
        if(accountRepository.findOneByUsername("palpatine") == null){
            save(new Account("palpatine", "palpatine", "admin"));
        }
    }

    @Transactional
    void save(Account user) {
        user.setPassword(user.getPassword());
         accountRepository.save(user);
    }
}
