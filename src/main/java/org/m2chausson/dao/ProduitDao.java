package org.m2chausson.dao;

import org.m2chausson.entities.Produit;

import javax.persistence.*;
import java.util.List;

public class ProduitDao {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceM2Chausson");

    private final EntityManager em;

    public ProduitDao() {
        this.em = entityManagerFactory.createEntityManager();
    }

    // Récupérer tous les Produits
    public List<Produit> getAllProduits() throws Exception {
        try {

            return em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupérations des produits", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Récupérer un produit par ID
    public Produit getProduitById(int id) throws Exception {

        try {

            return em.find(Produit.class, id);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupération d'un produit par ID", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Créer un nouveau Produit
    public void createProduit(Produit produit) throws Exception {
        try {

            em.getTransaction().begin();
            em.persist(produit);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Erreur lors de la création d'un produit", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Mettre à jour un enregistrement de Produit existant
    public void updateProduit(Produit produit) throws Exception {
        try {

            em.getTransaction().begin();
            em.merge(produit);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error while updating Produit", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Supprimer un enregistrement de Produit
    public void deleteProduit(Produit produit) throws Exception {
        try {

            em.getTransaction().begin();
            produit = em.merge(produit);
            em.remove(produit);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error while deleting Produit", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}