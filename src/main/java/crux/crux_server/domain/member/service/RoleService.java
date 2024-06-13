package crux.crux_server.domain.member.service;

import crux.crux_server.domain.member.entity.Role;
import crux.crux_server.domain.member.enums.RoleName;
import crux.crux_server.domain.member.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    @PostConstruct
    protected void init() {
        for (RoleName roleName : RoleName.values()) {
            if (!roleRepository.existsByName(roleName.name())) {
                roleRepository.save(Role.builder()
                        .name(roleName.name()).build()
                );
            }
        }
    }
}
