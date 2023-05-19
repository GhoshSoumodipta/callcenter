package at.ac.tuwien.sepm.groupphase.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthCheck {

    private final String path;

    private final boolean authorized;

    public AuthCheck(@JsonProperty("path") String path, @JsonProperty("authorized") boolean authorized) {
        super();
        this.path = path;
        this.authorized = authorized;
    }

    public String getPath() {
        return path;
    }

    public boolean isAuthorized() {
        return authorized;
    }
}
