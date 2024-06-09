package crux.crux_server.domain.member.repository;

import crux.crux_server.domain.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String roleName);

    Optional<Role> findByName(String roleName);
}
