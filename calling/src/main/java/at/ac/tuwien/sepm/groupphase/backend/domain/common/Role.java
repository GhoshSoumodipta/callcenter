package at.ac.tuwien.sepm.groupphase.backend.domain.common;

import org.springframework.security.core.GrantedAuthority;

public enum Role  implements GrantedAuthority{
    USERS, GUEST, ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
