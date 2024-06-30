package crux.crux_server.domain.member.repository;

import crux.crux_server.domain.member.entity.Role;
import crux.crux_server.domain.member.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(RoleName name);

    Optional<Role> findByName(RoleName name);
}
