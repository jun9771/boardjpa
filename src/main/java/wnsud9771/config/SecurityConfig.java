package wnsud9771.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;	

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests( (authorizeHttpRequest) ->
		authorizeHttpRequest.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		.csrf((csrf)-> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/**")))
		.formLogin((formLogin)-> formLogin.loginPage("/user/login").defaultSuccessUrl("/notice/list"))
		.logout((logout)-> logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/notice/list").invalidateHttpSession(true));
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();	
	}
}
