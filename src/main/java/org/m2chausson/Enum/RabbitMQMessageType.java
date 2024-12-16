package org.m2chausson.Enum;

import org.m2chausson.entities.Client;
import org.m2chausson.entities.Commande;
import org.m2chausson.entities.Produit;

import java.io.Serializable;

public enum RabbitMQMessageType implements Serializable {
    CREATE_ORDER(Commande.class),
    UPDATE_ORDER(Commande.class),
    DELETE_ORDER(String.class),

    CREATE_PRODUIT(Produit.class),
    UPDATE_PRODUIT(Produit.class),
    DELETE_PRODUIT(String.class),

    CREATE_CLIENT(Client.class),
    UPDATE_CLIENT(Client.class),
    DELETE_CLIENT(String.class),

    TEST_USER(Client.class);

    private final Class<?> expectedType;

    RabbitMQMessageType(Class<?> expectedType) {
        this.expectedType = expectedType;
    }

    public boolean isValidType(Object type) {
        return expectedType.equals(type);
    }
}
