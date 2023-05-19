package at.ac.tuwien.sepm.groupphase.backend.configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import at.ac.tuwien.sepm.groupphase.backend.security.HeaderTokenAuthenticationFilter;

public class HeaderTokenAuthenticationDsl extends AbstractHttpConfigurer<HeaderTokenAuthenticationDsl, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilterAfter(new HeaderTokenAuthenticationFilter(authenticationManager),
				UsernamePasswordAuthenticationFilter.class);
    }

    public static HeaderTokenAuthenticationDsl customDsl() {
        return new HeaderTokenAuthenticationDsl();
    }
}
