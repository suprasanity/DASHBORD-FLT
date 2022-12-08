package com.example.dashboard.model;

import javax.persistence.*;

@Entity
@Table(name="website")
public class WebService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="url")
    private String url;

    @Column(name="status")
    private String status;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public String getUrl() {
        return url;
    }
    public String getStatus() {return status;}


}
