package crux.crux_server.domain.member.repository;

import crux.crux_server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@GraphQlRepository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByOauth2id(String oauth2Id);
}
