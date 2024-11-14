package org.m2chausson.dao;

import org.m2chausson.entities.ItemCommande;

import javax.persistence.*;
import java.util.List;

public class ItemCommandeDao {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceM2Chausson");

    public ItemCommandeDao(EntityManagerFactory entityManagerFactory) {}

    // Récupérer tous les ItemCommandes
    public List<ItemCommande> getAllItemCommandes() throws Exception {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery("SELECT p FROM ItemCommande p", ItemCommande.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupérations des ItemCommandes", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Récupérer un ItemCommande par ID
    public ItemCommande getItemCommandeById(int id) throws Exception {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.find(ItemCommande.class, id);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupération d'un ItemCommande par ID", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Créer un nouveau ItemCommande
    public void createItemCommande(ItemCommande ItemCommande) throws Exception {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(ItemCommande);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new Exception("Erreur lors de la création d'un ItemCommande", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Mettre à jour un enregistrement de ItemCommande existant
    public void updateItemCommande(ItemCommande ItemCommande) throws Exception {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(ItemCommande);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new Exception("Error while updating ItemCommande", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Supprimer un enregistrement de ItemCommande
    public void deleteItemCommande(ItemCommande ItemCommande) throws Exception {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            ItemCommande = entityManager.merge(ItemCommande);
            entityManager.remove(ItemCommande);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new Exception("Error while deleting ItemCommande", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}