package crux.crux_server.config.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface Principal {
    Collection<? extends GrantedAuthority> getAuthorities();
}
