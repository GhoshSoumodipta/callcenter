package at.ac.tuwien.sepm.groupphase.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.util.*;

public class MsgUser implements UserDetails {
    @Serial
    private static final long serialVersionUID = -2960594552534067419L;

    @Id
    private ObjectId id;
    @NotNull
    @JsonProperty
    private Date createdAt = new Date();
    @NotBlank
    @Size(min=4)
    @JsonProperty
    private String username;
    @NotBlank
    @Size(min=4)
    @JsonProperty
    private String password;
    @JsonProperty
    private String email;
    @JsonProperty
    private String token;
    @JsonProperty
    private String base64Avatar;
    @JsonProperty
    private String publicKey;
    @JsonProperty
    private String privateKey;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String salt;
    @JsonProperty
    private boolean confirmed = false;
    @JsonProperty
    private String uuid;
    @JsonProperty
    private List<ObjectId> contacts = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority auth = () -> "USERS";
        return Arrays.asList(auth);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<ObjectId> getContacts() {
        return contacts;
    }

    public void setContacts(List<ObjectId> contacts) {
        this.contacts = contacts;
    }
}
