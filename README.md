# TP13 - OAuth2 Authentication with Spring Security

## Overview
OAuth2 / OpenID Connect login using Google and Keycloak as Identity Providers.

## Credentials setup
- Set `GOOGLE_CLIENT_ID` in your local environment
- Set `GOOGLE_CLIENT_SECRET` in your local environment
- Register this redirect URI in Google Cloud: `http://localhost:8080/login/oauth2/code/google`
## tests 
<img width="1911" height="820" alt="image" src="https://github.com/user-attachments/assets/94d798ee-6622-4777-aff6-fcab788ec553" />
<img width="1895" height="765" alt="image" src="https://github.com/user-attachments/assets/eaea5e6a-88af-455b-ab4a-9a313b4cdcde" />
<img width="1899" height="797" alt="image" src="https://github.com/user-attachments/assets/885574da-4bca-4c4f-8942-a47fd44641a9" />

## Run the application
```bash
mvn spring-boot:run
```
Then open: http://localhost:8080

## OAuth2 Flow
1. User visits `/profile` and Spring redirects to Google login
2. Google redirects back to `/login/oauth2/code/google`
3. Spring exchanges the code for an access token and ID token
4. User lands on `/profile` with their name, email, and photo

## Project Structure
```text
src/main/java/ma/ens/security/
|-- Oauth2ClientApplication.java      Main entry point
|-- config/SecurityConfig.java        Security rules
`-- web/HomeController.java           Controllers

src/main/resources/
|-- application.yml                   OAuth2 config via env vars / placeholders
|-- templates/
|   |-- index.html
|   |-- login.html
|   `-- profile.html
`-- static/css/style.css
```

## Keycloak
Run Keycloak on port 8180, create realm `ens-realm`, and client `spring-oauth-client`.
Update the Keycloak secret locally when you enable that provider.
