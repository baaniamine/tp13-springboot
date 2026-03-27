package ma.ens.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class — Step 5 of the TP.
 *
 * This class overrides the default Spring Security auto-configuration
 * and defines custom rules for:
 *   - Which URLs are publicly accessible (no login required)
 *   - Which URLs require authentication
 *   - OAuth2 login behavior (custom login page, redirect after success)
 *   - Logout behavior (session invalidation, cookie cleanup)
 */
@Configuration
public class SecurityConfig {

    /**
     * Defines the security filter chain applied to every HTTP request.
     *
     * @param http the HttpSecurity builder provided by Spring Security
     * @return the configured SecurityFilterChain bean
     * @throws Exception if any configuration error occurs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // -------------------------------------------------------
            // Authorization rules:
            //   - "/" (home page)  → public, no login needed
            //   - "/login**"       → public, needed to show our custom login page
            //   - "/css/**"        → public, static resources must be accessible
            //   - Everything else  → must be authenticated
            // -------------------------------------------------------
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login**", "/css/**").permitAll()
                .anyRequest().authenticated()
            )

            // -------------------------------------------------------
            // OAuth2 Login configuration:
            //   - loginPage: points to our custom login page (HomeController)
            //   - defaultSuccessUrl: where to redirect after successful login
            //     (true = always redirect here, even if there was no previous target URL)
            // -------------------------------------------------------
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("/profile", true)
            )

            // -------------------------------------------------------
            // Logout configuration:
            //   - logoutSuccessUrl: redirect to home page after logout
            //   - invalidateHttpSession: destroys the server-side HTTP session
            //   - clearAuthentication: clears the SecurityContext (removes user info)
            //   - deleteCookies: removes the JSESSIONID cookie from the browser
            // -------------------------------------------------------
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
            );

        return http.build();
    }
}
