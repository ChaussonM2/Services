package org.m2chausson.dao;

import org.m2chausson.entities.Commande;

import javax.persistence.*;
import java.util.List;

public class CommandeDao {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceM2Chausson");

    private final EntityManager em;

    public CommandeDao() {
        this.em = entityManagerFactory.createEntityManager();
    }

    // Récupérer tous les Commandes
    public List<Commande> getAllCommandes() throws Exception {
        try {
            return em.createQuery("SELECT p FROM Commande p", Commande.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupérations des Commandes", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Récupérer un Commande par ID
    public Commande getCommandeById(int id) throws Exception {
        try {
            return em.find(Commande.class, id);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupération d'un Commande par ID", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Créer un nouveau Commande
    public void createCommande(Commande Commande) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(Commande);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Erreur lors de la création d'un Commande", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Mettre à jour un enregistrement de Commande existant
    public void updateCommande(Commande Commande) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(Commande);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error while updating Commande", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Supprimer un enregistrement de Commande
    public void deleteCommande(Commande Commande) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(Commande);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error while deleting Commande", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}