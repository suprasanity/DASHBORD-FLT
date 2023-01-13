package com.example.dashboard.service;

import com.example.dashboard.DAO.DAOUser;
import com.example.dashboard.DAO.DAOWSStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {
    @Autowired
    private DAOUser daoUser;

    public boolean isLogged(String login, String password)
    {
        if (daoUser.getUserByLogin(login,password) != null)
        {
            return true;
        }
        return false;
    }

    public boolean register(String login, String password, String email, String tel) {
        if (daoUser.getUserByLogin(login,password) == null)
        {
            daoUser.save(login,password,email,tel);
            return true;
        }
        return false;
    }
}
