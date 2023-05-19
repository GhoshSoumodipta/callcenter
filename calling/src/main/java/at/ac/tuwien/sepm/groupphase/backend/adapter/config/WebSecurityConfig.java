package at.ac.tuwien.sepm.groupphase.backend.adapter.config;

import at.ac.tuwien.sepm.groupphase.backend.domain.common.JwtTokenProvider;
import at.ac.tuwien.sepm.groupphase.backend.domain.common.Role;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class WebSecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        HttpSecurity result = httpSecurity
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeHttpRequests(authorize -> authorize.antMatchers("/rest/auth/**").permitAll())
            .authorizeRequests(authorize -> authorize.antMatchers("/rest/**").hasAuthority(Role.USERS.toString()))
            .authorizeRequests(authorize -> authorize.antMatchers("/**").permitAll())
            .csrf().disable()
            .apply(new JwtTokenFilterConfigurer(jwtTokenProvider)).and();
            return result.build();
    }
}
