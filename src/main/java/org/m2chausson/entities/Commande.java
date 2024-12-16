package org.m2chausson.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "commande")
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany
    @JoinColumn(name = "id_itemCommande")
    private List<ItemCommande> itemCommande;
    
    public Commande() {}

    public Commande(List<ItemCommande> itemCommande, Client client) {
        this.itemCommande=itemCommande;
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClientId(Client client) {
        this.client = client;
    }

    public List<ItemCommande> getitemCommande() {
        return itemCommande;
    }

    public void setitemCommande(List<ItemCommande> itemCommande) {
        this.itemCommande = itemCommande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Commande [id=" + id + ", clientId=" + client.getId() + ", itemCommande=" + itemCommande + "]";
    }
}

