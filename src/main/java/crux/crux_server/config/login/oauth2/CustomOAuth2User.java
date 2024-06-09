package crux.crux_server.config.login.oauth2;

import org.springframework.security.oauth2.core.user.OAuth2User;

public interface CustomOAuth2User extends OAuth2User {
    String getOauth2Id();
    String getRegistrationId();
    Long getMemberId();
}
