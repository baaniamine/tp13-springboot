package ma.ens.security.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main web controller — Step 4 of the TP.
 *
 * Handles navigation between the three main pages:
 *   - "/"        → public home page
 *   - "/login"   → custom login page showing OAuth2 provider buttons
 *   - "/profile" → protected page displaying the authenticated user's info
 *
 * Note: No controller is needed for the actual OAuth2 redirect to Google/Keycloak.
 * Spring Security handles the entire OAuth2 flow automatically.
 */
@Controller
public class HomeController {

    /**
     * Home page — publicly accessible, no authentication required.
     *
     * @return Thymeleaf template name → renders templates/index.html
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /**
     * Custom login page — publicly accessible.
     *
     * Displays buttons to trigger OAuth2 login via Google or Keycloak.
     * The actual redirect to the provider is handled by Spring Security
     * at /oauth2/authorization/{registrationId}.
     *
     * @return Thymeleaf template name → renders templates/login.html
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Profile page — requires authentication (enforced by SecurityConfig).
     *
     * If the user is not authenticated, Spring Security automatically
     * redirects them to the login page before reaching this method.
     *
     * @param model the Spring MVC Model used to pass attributes to the Thymeleaf template
     * @param user  the currently authenticated OAuth2 user, automatically injected by Spring.
     *
     *              @AuthenticationPrincipal resolves the principal from the SecurityContext
     *              and injects it as an OAuth2User object.
     *
     *              The user's attributes (name, email, picture) come from the ID Token (JWT)
     *              sent by the Identity Provider (Google or Keycloak) during the OAuth2 flow.
     *              These claims are part of the OpenID Connect standard.
     *
     * @return Thymeleaf template name → renders templates/profile.html
     */
    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal OAuth2User user) {
        // Extract user attributes from the ID Token claims provided by the IdP
        model.addAttribute("name", user.getAttribute("name"));        // Full name
        model.addAttribute("email", user.getAttribute("email"));      // Email address
        model.addAttribute("picture", user.getAttribute("picture"));  // Profile photo URL (Google)

        return "profile";
    }
}
