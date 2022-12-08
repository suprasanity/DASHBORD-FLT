package com.example.dashboard.service;

import com.example.dashboard.DAO.DAOWSStatus;
import com.example.dashboard.model.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceWSStatus {
    @Autowired
    private DAOWSStatus daoWSStatus;
    public void addWS(String urlWS)
    {
        daoWSStatus.save(urlWS);
    }
    public List getAllWS()
    {
       return daoWSStatus.getAll();
    }

    public void deleteWS(long parseLong) {
        daoWSStatus.delete(parseLong);
    }

    public String getStatus(long parseLong) {
        return daoWSStatus.getStatus(parseLong);
    }

    public WebService getWS(long parseLong) {
        return daoWSStatus.getWS(parseLong);
    }

    public void updateStatus(Long id, String down) {
        daoWSStatus.updateStatus(id,down);
    }
}
