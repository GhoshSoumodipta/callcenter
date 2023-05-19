package at.ac.tuwien.sepm.groupphase.backend.configuration;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import at.ac.tuwien.sepm.groupphase.backend.security.HeaderTokenAuthenticationFilter;
import at.ac.tuwien.sepm.groupphase.backend.service.implementation.AppUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private AppUserDetailsService userDetailsService;
	@Autowired
	private final PasswordEncoder passwordEncoder;

	public SecurityConfiguration(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new AppUserDetailsService();
	}

	@Bean
	public JdbcUserDetailsManager userDetailsManager(DataSource datasource) {
		JdbcUserDetailsManager mgr = new JdbcUserDetailsManager();
		mgr.setDataSource(dataSource);
		return mgr;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	/*
	 * @Bean public ErrorAttributes errorAttributes() { return new
	 * DefaultErrorAttributes() { public Map<String, Object>
	 * getErrorAttributes(RequestAttributes requestAttributes, boolean
	 * includeStackTrace) { Map<String, Object> errorAttributes =
	 * super.getErrorAttributes((WebRequest) requestAttributes, includeStackTrace);
	 * errorAttributes.remove("exception"); return errorAttributes; } }; }
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, List<AuthenticationProvider> providerList)
			throws Exception {

		auth.authenticationProvider(authenticationProvider());

		providerList.forEach(auth::authenticationProvider);

	}

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().headers().frameOptions().sameOrigin().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint((req, res, aE) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
				.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers(HttpMethod.GET,"/api/v1/objects/**")
				.hasAnyRole("ADMIN", "CUSTOMER", "COMPANY", "COMPANYEMPLOYEE", "COMPANYLEADER", "SWITCHINGCALLCENTER", "SWITCHINGCALLCENTERLEADER", "SWITCHINGCALLCENTEREMPLOYEE")
				.antMatchers(HttpMethod.POST,"/api/v1/objects")
				.hasAnyRole("ADMIN", "COMPANY", "COMPANYEMPLOYEE", "COMPANYLEADER", "SWITCHINGCALLCENTER", "SWITCHINGCALLCENTERLEADER", "SWITCHINGCALLCENTEREMPLOYEE")
				.antMatchers(HttpMethod.POST, "/authentication").permitAll().antMatchers(HttpMethod.POST, "/users")
				.permitAll().antMatchers(HttpMethod.GET, "/v2/api-docs", "/swagger-resources/**",
						"/webjars/springfox-swagger-ui/**", "/swagger-ui.html")
				.permitAll().antMatchers(HttpMethod.POST,"/api/v1/users/**").permitAll()
            .antMatchers("/caller/**/**").permitAll().antMatchers("/calls/**").permitAll();

		http.apply(headerTokenAuthenticationDsl());

		return http.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Bean
	HeaderTokenAuthenticationDsl headerTokenAuthenticationDsl() {
		return new HeaderTokenAuthenticationDsl();
	}
}