package org.m2chausson.RabbitMQ;

import org.m2chausson.dao.ClientDao;
import org.m2chausson.dao.CommandeDao;
import org.m2chausson.dao.ProduitDao;
import org.m2chausson.entities.Client;
import org.m2chausson.entities.Commande;
import org.m2chausson.entities.Produit;

public class RabbitMQMessageHandler {

    CommandeDao commandeDao = new CommandeDao();
    ProduitDao produitDao = new ProduitDao();
    ClientDao clientDao = new ClientDao();

    public void HandleMessage(RabbitMQMessage message) throws Exception {
        System.out.println("Récupération des données!");
        System.out.println("Message Type: " + message.getMessageType());
        System.out.println("Message data: " + message.getData());
        switch (message.getMessageType()){
            case CREATE_ORDER:
                commandeDao.createCommande((Commande) message.getData());
                break;
            case UPDATE_ORDER:
                commandeDao.updateCommande((Commande) message.getData());
                break;
            case DELETE_ORDER:
                commandeDao.deleteCommande((Commande)message.getData());
                break;

            case CREATE_PRODUIT:
                produitDao.createProduit((Produit) message.getData());
                break;
            case UPDATE_PRODUIT:
                produitDao.updateProduit((Produit) message.getData());
                break;
            case DELETE_PRODUIT:
                produitDao.deleteProduit((Produit)message.getData());
                break;

            case CREATE_CLIENT:
                clientDao.createClient((Client) message.getData());
                break;
            case UPDATE_CLIENT:
                clientDao.updateClient((Client) message.getData());
                break;
            case DELETE_CLIENT:
                clientDao.deleteClient((Client)message.getData());
                break;

            default:
                break;
        }
    }
}
