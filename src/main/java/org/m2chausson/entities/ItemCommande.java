package org.m2chausson.entities;

import javax.persistence.*;

@Entity
@Table(name = "ItemCommande")
public class ItemCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produit")
    private Produit produit;

    @Column(name = "Qte", nullable = false)
    private int quantity;
    
    public ItemCommande() {}

    public ItemCommande(int id_commande, Produit produit, int quantity) {
        this.produit=produit;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "ItemCommande [id=" + id + ", Produit=" + produit.getNom() + ", quantity=" + quantity +"]";
    }
}

