package ma.ens.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Basic integration test that verifies the Spring application context loads correctly.
 *
 * We override the OAuth2 credentials with dummy values so the context
 * can start without making real network calls to Google or Keycloak.
 */
@SpringBootTest
@TestPropertySource(properties = {
    "spring.security.oauth2.client.registration.google.client-id=test-id",
    "spring.security.oauth2.client.registration.google.client-secret=test-secret",
    "spring.security.oauth2.client.registration.keycloak.client-id=test-kc-id",
    "spring.security.oauth2.client.registration.keycloak.client-secret=test-kc-secret",
    "spring.security.oauth2.client.provider.keycloak.issuer-uri=http://localhost:9999/realms/test"
})
class Oauth2ClientApplicationTests {

    @Test
    void contextLoads() {
        // Verifies that all Spring beans are correctly wired
        // and the security configuration is valid
    }
}
