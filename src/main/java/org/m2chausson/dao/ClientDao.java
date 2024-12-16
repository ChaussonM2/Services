package org.m2chausson.dao;

import org.m2chausson.entities.Client;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClientDao {

    public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceM2Chausson");

    private final EntityManager em;

    public ClientDao() {
        em = entityManagerFactory.createEntityManager();
    }

    // Récupérer tous les clients
    public List<Client> getAllClients() throws Exception {
        try {
            return em.createQuery("SELECT c FROM Client c").getResultList();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la récupération de tous les clients", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Récupérer un client par ID
    public Client getClientById(int id) throws Exception {
        try {
            return em.find(Client.class, id);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la recherche d'un client par son identifiant", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Créer un nouveau client
    public void createClient(Client client) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Erreur lors de l'ajout d'un nouveau client", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Mettre à jour un client existant
    public void updateClient(Client client) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Erreur lors de la mise à jour du client", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Supprimer un client
    public void deleteClient(Client client) throws Exception {
        try {
            em.getTransaction().begin();
            client = em.merge(client);
            em.remove(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Erreur lors de la suppression du client", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
