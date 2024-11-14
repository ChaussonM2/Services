package org.m2chausson.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_commande;

    @Column(name = "id_client", nullable = false)
    private int id_client;

    @Column(name = "itemCommande", nullable = false)
    private List<ItemCommande> itemCommande;
    
    public Commande() {}

    public Commande(int id_commande, List<ItemCommande> itemCommande, int id_client) {
        this.id_commande = id_commande;
        this.itemCommande=itemCommande;
        this.id_client = id_client;
    }

    public int getClientId() {
        return id_client;
    }

    public void setClientId(int ClientId) {
        this.id_client = ClientId;
    }

    public List<ItemCommande> getitemCommande() {
        return itemCommande;
    }

    public void setitemCommande(List<ItemCommande> itemCommande) {
        this.itemCommande = itemCommande;
    }

    public int getId() {
        return id_commande;
    }

    public void setId(int id) {
        this.id_commande = id;
    }

    @Override
    public String toString() {
        return "Commande [id=" + id_commande + ", clientId=" + id_client + ", itemCommande=" + itemCommande + "]";
    }
}

