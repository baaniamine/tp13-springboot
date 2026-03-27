package ma.ens.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the OAuth2 Client Spring Boot application.
 *
 * This application demonstrates OAuth2 / OpenID Connect login
 * using Google and Keycloak as Identity Providers (IdP).
 *
 * Spring Boot auto-configures the OAuth2 login flow based on
 * the settings defined in application.yml — no manual controller
 * is needed for the login redirect itself.
 */
@SpringBootApplication
public class Oauth2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ClientApplication.class, args);
    }
}
