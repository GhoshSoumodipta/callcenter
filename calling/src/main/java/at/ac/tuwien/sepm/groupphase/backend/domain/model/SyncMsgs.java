package at.ac.tuwien.sepm.groupphase.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SyncMsgs {
    @NotNull
    @JsonProperty
    private Date lastUpdate;
    @NotBlank
    @JsonProperty
    private String ownId;
    @JsonProperty
    private List<String> contactIds = new ArrayList<>();
    @JsonProperty
    private List<Message> msgs = new ArrayList<>();

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getOwnId() {
        return ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }

    public List<String> getContactIds() {
        return contactIds;
    }

    public void setContactIds(List<String> contactIds) {
        this.contactIds = contactIds;
    }

    public List<Message> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<Message> msgs) {
        this.msgs = msgs;
    }
}
