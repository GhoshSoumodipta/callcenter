package at.ac.tuwien.sepm.groupphase.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Component
public class Message {
    @Id
    private ObjectId id;
    @NotBlank
    @JsonProperty
    private String fromId;
    @NotBlank
    @JsonProperty
    private String toId;
    @JsonProperty
    private Long localId;
    @JsonProperty
    private Date timestamp;
    @JsonProperty
    private String text;
    @JsonProperty
    private String filename;
    @JsonProperty
    private boolean send;
    @JsonProperty
    private boolean received;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public Long getLocalId() {
        return localId;
    }

    public void setLocalId(Long localId) {
        this.localId = localId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }
}
