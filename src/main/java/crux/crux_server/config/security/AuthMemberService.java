package crux.crux_server.config.security;

import crux.crux_server.domain.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AuthMemberService implements UserDetailsService {

    // todo: 로그인 과정 구현
    @Override
    public AuthMember loadUserByUsername(String phoneNumber) throws MemberException {

        return AuthMember.builder()
                .build();
    }
}
