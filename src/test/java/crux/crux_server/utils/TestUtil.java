package crux.crux_server.utils;

import crux.crux_server.domain.member.entity.Member;
import crux.crux_server.domain.member.entity.Role;
import crux.crux_server.domain.member.enums.RoleName;
import crux.crux_server.domain.member.repository.MemberRepository;
import crux.crux_server.domain.member.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUtil {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 메서드 prefix 설명
     * get : 해당 엔티티를 찾아서 반환. 없으면 생성해서 반환함.
     * create: 엔티티를 생성해서 반환함.
     * **/

    public Role getRole(RoleName roleName) {
        return roleRepository.findByName(RoleName.USER)
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .name(roleName)
                        .build()));
    }

    public Member createMember() {
        Role role = getRole(RoleName.USER);
        return memberRepository.save(Member.builder()
                .oauth2id("test:oauth2id")
                .name("testName")
                .role(role)
                .build());
    }
}
