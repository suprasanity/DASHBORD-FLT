package com.example.dashboard.DAO;

import com.example.dashboard.model.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Repository
public class DAOWSStatus  {
    @PersistenceContext
    private EntityManager em;



    @Transactional
    public void save(String urlWS) {
        WebService webService = new WebService();
        webService.setUrl(urlWS);

        try{
            URL url = new URL(urlWS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            webService.setStatus(String.valueOf(con.getResponseCode()));

        }catch (Exception e){
            webService.setStatus("CODE ERROR INCONNU");
            em.persist(webService);
        }
        em.persist(webService);
        }
    @Transactional
    public List getAll() {
        return em.createQuery("SELECT w FROM WebService w").getResultList();
    }
    @Transactional
    public void delete(Long id) {
        em.createQuery("DELETE FROM WebService w WHERE w.id = :id").setParameter("id", id).executeUpdate();
    }

    @Transactional
    public String getStatus(long parseLong) {
        return (String) em.createQuery("SELECT w.status FROM WebService w WHERE w.id = :id").setParameter("id", parseLong).getSingleResult();
    }
    @Transactional
    public WebService getWS(long parseLong) {
        return (WebService) em.createQuery("SELECT w FROM WebService w WHERE w.id = :id").setParameter("id", parseLong).getSingleResult();
    }

    @Transactional
    public void updateStatus(Long id, String down) {
        em.createQuery("UPDATE WebService w SET w.status = :status WHERE w.id = :id").setParameter("id", id).setParameter("status", down).executeUpdate();
    }
}

