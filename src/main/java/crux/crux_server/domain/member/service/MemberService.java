package crux.crux_server.domain.member.service;

import crux.crux_server.domain.member.entity.Member;
import crux.crux_server.domain.member.exception.MemberException;
import crux.crux_server.domain.member.repository.MemberRepository;
import crux.crux_server.domain.member.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "member-service")
public class MemberService {
    private final MemberRepository memberRepository;

    // 멤버 엔티티 생성
    @Transactional
    public Member createMember(String phoneNumber, String kakaoId) throws MemberException {
        // todo: 중복 체크

        // todo: 멤버 생성
        Member member = Member.builder()
                .build();

        return memberRepository.save(member);
    }

    // 멤버 조회
    public Member getMember(Integer id) {
        return memberRepository.findById(id)
                .orElseThrow(MemberException.MemberNotFoundException::new);
    }
}
