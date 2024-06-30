package crux.crux_server.config.security;

import crux.crux_server.domain.member.enums.RoleName;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthMember implements Principal {
    private final Long id;
    private final RoleName role;

    @Builder
    public AuthMember(Long id, RoleName role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
        return List.of(authority);
    }
}
