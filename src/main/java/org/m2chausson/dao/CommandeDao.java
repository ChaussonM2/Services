package org.m2chausson.dao;

import org.m2chausson.entities.Commande;

import javax.persistence.*;
import java.util.List;

public class CommandeDao {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceM2Chausson");

    public CommandeDao(EntityManagerFactory entityManagerFactory) {}

    // Récupérer tous les Commandes
    public List<Commande> getAllCommandes() throws Exception {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery("SELECT p FROM Commande p", Commande.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupérations des Commandes", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Récupérer un Commande par ID
    public Commande getCommandeById(int id) throws Exception {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.find(Commande.class, id);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupération d'un Commande par ID", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Créer un nouveau Commande
    public void createCommande(Commande Commande) throws Exception {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(Commande);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new Exception("Erreur lors de la création d'un Commande", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Mettre à jour un enregistrement de Commande existant
    public void updateCommande(Commande Commande) throws Exception {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(Commande);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new Exception("Error while updating Commande", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // Supprimer un enregistrement de Commande
    public void deleteCommande(Commande Commande) throws Exception {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Commande = entityManager.merge(Commande);
            entityManager.remove(Commande);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new Exception("Error while deleting Commande", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}