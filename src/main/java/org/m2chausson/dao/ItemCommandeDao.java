package org.m2chausson.dao;

import org.m2chausson.entities.ItemCommande;

import javax.persistence.*;
import java.util.List;

public class ItemCommandeDao {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceM2Chausson");

    private final EntityManager em;

    public ItemCommandeDao() {
        this.em = entityManagerFactory.createEntityManager();
    }

    // Récupérer tous les ItemCommandes
    public List<ItemCommande> getAllItemCommandes() throws Exception {
        try {
            return em.createQuery("SELECT p FROM ItemCommande p", ItemCommande.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupérations des ItemCommandes", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Récupérer un ItemCommande par ID
    public ItemCommande getItemCommandeById(int id) throws Exception {
        try {
            return em.find(ItemCommande.class, id);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupération d'un ItemCommande par ID", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Créer un nouveau ItemCommande
    public void createItemCommande(ItemCommande ItemCommande) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(ItemCommande);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Erreur lors de la création d'un ItemCommande", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Mettre à jour un enregistrement de ItemCommande existant
    public void updateItemCommande(ItemCommande ItemCommande) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(ItemCommande);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error while updating ItemCommande", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Supprimer un enregistrement de ItemCommande
    public void deleteItemCommande(ItemCommande ItemCommande) throws Exception {
        try {
            em.getTransaction().begin();
            ItemCommande = em.merge(ItemCommande);
            em.remove(ItemCommande);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error while deleting ItemCommande", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}