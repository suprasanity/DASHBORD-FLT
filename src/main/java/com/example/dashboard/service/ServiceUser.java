package com.example.dashboard.service;

import com.example.dashboard.dao.DAOUser;
import com.example.dashboard.model.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUser {
    @Autowired
    private DAOUser daoUser;

    public long isLogged(String login, String password)
    {
       return daoUser.getUserByLogin(login,password);
    }

    public boolean register(String login, String password, String email, String tel) {
        if (daoUser.getUserByLogin(login,password) == null)
        {
            daoUser.save(login,password,email,tel);
            return true;
        }
        return false;
    }
    public List<Tache> getTachesByUserId(int id) {
        return daoUser.getTachesByUserId(id);
    }
}
