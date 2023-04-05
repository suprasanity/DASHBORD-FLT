package com.example.dashboard.model;
import javax.persistence.*;
@Entity
@Table(name = "tache")
public class Tache {
    public int getIdTache() {
        return idTache;
    }

    public void setIdTache(int idTache) {
        this.idTache = idTache;
    }

    public String getNomTache() {
        return nomTache;
    }

    public void setNomTache(String nomTache) {
        this.nomTache = nomTache;
    }

    public String getDatTache() {
        return datTache;
    }

    public void setDatTache(String datTache) {
        this.datTache = datTache;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tache_id")
    private int idTache;

    @Column(name = "tache_nom")
    private String nomTache;

    @Column(name = "tache_date")
    private String datTache;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // constructors, getters and setters
}


