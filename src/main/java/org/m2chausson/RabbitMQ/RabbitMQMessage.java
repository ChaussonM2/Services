package org.m2chausson.RabbitMQ;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import org.m2chausson.Enum.RabbitMQMessageType;

import java.io.Serializable;

@Data
public class RabbitMQMessage<T> implements Serializable {
    @NonNull
    private RabbitMQMessageType messageType;

    private T data;

    public RabbitMQMessage() {
        // Default constructor required for deserialization
    }

    @JsonCreator
    public RabbitMQMessage(RabbitMQMessageType messageType, T data) {
        this.messageType = messageType;
        if (!messageType.isValidType(data.getClass())) {
            throw new IllegalArgumentException("Type mismatch");
        }
        this.data = data;
    }

}
