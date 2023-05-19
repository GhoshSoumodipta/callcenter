package at.ac.tuwien.sepm.groupphase.backend.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Contact {

    @NotBlank
    @Size(min = 4)
    private String name;

    private String base64Avatar;

    @NotBlank
    private String publicKey;

    @NotBlank
    private String userId;

    public Contact() {
        /**
         * no code for default constructor
         */
    }

    public Contact(String name, String base64Avatar, String publicKey, String userId) {
        super();
        this.name = name;
        this.base64Avatar = base64Avatar;
        this.publicKey = publicKey;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase64Avatar() {
        return base64Avatar;
    }

    public void setBase64Avatar(String base64Avatar) {
        this.base64Avatar = base64Avatar;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
