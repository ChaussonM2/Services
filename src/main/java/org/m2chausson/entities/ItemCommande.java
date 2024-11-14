package org.m2chausson.entities;

import javax.persistence.*;

@Entity
@Table(name = "ItemCommande")
public class ItemCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany( targetEntity=Commande.class, mappedBy="Commande" )
    @Column(name = "id_commande", nullable = false)
    private int id_commande;

    @Column(name = "produit", nullable = false)
    private Produit produit;

    @Column(name = "Qte", nullable = false)
    private int quantity;
    
    public ItemCommande() {}

    public ItemCommande(int id, int id_commande, Produit produit, int quantity) {
        this.id = id;
        this.id_commande = id_commande;
        this.produit=produit;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCommande() {
        return id;
    }

    public void setIdCommande(int id_commande) {
        this.id_commande = id_commande;
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
        return "ItemCommande [id=" + id + ", Produit=" + produit + ", quantity=" + quantity + ", idCommande = " + id_commande +"]";
    }
}

