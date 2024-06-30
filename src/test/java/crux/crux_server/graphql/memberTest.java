package crux.crux_server.graphql;

import crux.crux_server.domain.member.entity.Member;
import crux.crux_server.utils.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@DisplayName("member 테스트")
@AutoConfigureGraphQlTester
public class memberTest {
    @Autowired
    private GraphQlTester graphQlTester;
    @Autowired
    private TestUtil testUtil;

    @Test
    @DisplayName("멤버 조회 테스트")
    void createMemberTest() {
        // given
        Member member = testUtil.createMember();

        // when
        GraphQlTester.Response response = graphQlTester.documentName("getMember")
                .variable("id", member.getId())
                .execute();

        // then
        response.path("getMember.id").entity(Long.class).isEqualTo(member.getId());
    }
}
