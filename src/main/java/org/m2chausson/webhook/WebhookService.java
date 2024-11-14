package org.m2chausson.webhook;

import org.m2chausson.dao.WebhookDao;
import org.m2chausson.entities.Client;
import org.m2chausson.entities.Produit;

public class WebhookService {

    private final WebhookDao webhookDao;

    public WebhookService(WebhookDao webhookDao) {
        this.webhookDao = webhookDao;
    }

    public enum Notification {
        //Appel pour les méthodes Produits
        PRODUIT_UPDATE {
            @Override
            public void executeAction(WebhookDao webhookDao, Object message) {
                if (message instanceof Produit) {
                    webhookDao.majProduit((Produit) message);
                } else {
                    throw new IllegalArgumentException("Le message doit être un Produit pour PRODUIT_UPDATE");
                }
            }
        },
        PRODUIT_CREATE {
            @Override
            public void executeAction(WebhookDao webhookDao, Object message) {
                if (message instanceof Produit) {
                    webhookDao.creerProduit((Produit) message);
                } else {
                    throw new IllegalArgumentException("Le message doit être un Produit pour PRODUIT_CREATE");
                }
            }
        },
        PRODUIT_DELETE {
            @Override
            public void executeAction(WebhookDao webhookDao, Object message) {
                if (message instanceof Produit) {
                    webhookDao.supprimerProduit((Produit) message);
                } else {
                    throw new IllegalArgumentException("Le message doit être un Produit pour PRODUIT_DELETE");
                }
            }
        },

        // Appel pour les méthodes Clients
        CLIENT_UPDATE {
            @Override
            public void executeAction(WebhookDao webhookDao, Object message) {
                if (message instanceof Client) {
                    webhookDao.majClient((Client) message);
                } else {
                    throw new IllegalArgumentException("Le message doit être un Client pour CLIENT_UPDATE");
                }
            }
        },
        CLIENT_CREATE {
            @Override
            public void executeAction(WebhookDao webhookDao, Object message) {
                if (message instanceof Client) {
                    webhookDao.creerClient((Client) message);
                } else {
                    throw new IllegalArgumentException("Le message doit être un Client pour CLIENT_CREATE");
                }
            }
        },
        CLIENT_DELETE {
            @Override
            public void executeAction(WebhookDao webhookDao, Object message) {
                if (message instanceof Client) {
                    webhookDao.supprimerClient((Client) message);
                } else {
                    throw new IllegalArgumentException("Le message doit être un Client pour CLIENT_DELETE");
                }
            }
        };
        public abstract void executeAction(WebhookDao webhookDao, Object message);
    }

    public String sendMessage(Notification notificationType, Object message) {
        try {
            notificationType.executeAction(webhookDao, message);
            return "Action exécutée avec succès pour " + notificationType.name();
        } catch (Exception e) {
            return "Erreur lors de l'exécution de l'action pour " + notificationType.name() + " : " + e.getMessage();
        }
    }
}
