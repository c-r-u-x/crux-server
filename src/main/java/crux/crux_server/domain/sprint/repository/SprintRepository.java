package crux.crux_server.domain.sprint.repository;

import crux.crux_server.domain.sprint.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface SprintRepository extends JpaRepository<Sprint, Long>{
}
