package crux.crux_server.config.login.jwt;

import crux.crux_server.config.EnvBean;
import crux.crux_server.config.login.exception.JwtCustomException;
import crux.crux_server.config.security.AuthMember;
import crux.crux_server.domain.member.entity.Member;
import crux.crux_server.domain.member.exception.MemberException;
import crux.crux_server.domain.member.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Date;

// 토큰을 생성하고 검증하는 클래스입니다.
// 해당 컴포넌트는 필터클래스에서 사전 검증을 거칩니다.
@Component
@Slf4j(topic = "JwtTokenProvider")
@RequiredArgsConstructor
public class JwtTokenProvider {
    private String secretKey;
    private final EnvBean envBean;
    private final MemberRepository memberRepository;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(envBean.getJwtSecretKey().getBytes());
    }

    // JWT 토큰 생성
    @Transactional
    public String createToken(Long memberId, JwtTokenType tokenType) throws MemberException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberException.MemberNotFoundException::new);
        // 토큰 생성
        Claims claims = Jwts.claims().setSubject(member.getId().toString()); // JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
        claims.put("type", tokenType.getTokenType()); // 토큰 타입 (access token, refresh token)
        Date now = new Date();
        Date expiration;

        if (tokenType == JwtTokenType.REFRESH_TOKEN) { // refresh token
            expiration = new Date(now.getTime() + envBean.getRefreshTokenTime());
        } else if (tokenType == JwtTokenType.ACCESS_TOKEN) { // access token
            expiration = new Date(now.getTime() + envBean.getAccessTokenTime());
        } else {
            throw new JwtCustomException.JwtInvalidException();
        }

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(expiration) // set Expire Time
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256) // signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    @Transactional
    public Authentication getAuthentication(Jws<Claims> claimsJws) throws MemberException {
        String memberId = claimsJws.getBody().getSubject();

        Member member = memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(MemberException.MemberNotFoundException::new);

        // todo: Member 테이블 결정 후 AuthMember 객체 수정
        AuthMember authMember = AuthMember.builder()
                .id(member.getId())
                .role(member.getRole().getName())
                .build();

        return new UsernamePasswordAuthenticationToken(authMember, "", authMember.getAuthorities());
    }

//    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : Bearer [TOKEN 값]'
    public String resolveToken(HttpServletRequest request) {
        try {
            String value = request.getHeader("Authorization");
            if (value != null && value.startsWith("Bearer ")) {
                return value.substring(7);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw  new JwtException("토큰이 유효하지 않습니다.");
        }
    }

//    // 토큰의 유효성 + 만료일자 확인
    public Jws<Claims> validateAndParseToken(String jwt) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes()).build();
        return jwtParser.parseClaimsJws(jwt);
    }

    public boolean isRefreshToken(Jws<Claims> jws) {
        return jws.getBody().get("type").equals(JwtTokenType.REFRESH_TOKEN.getTokenType());
    }

    public boolean isAccessToken(Jws<Claims> jws) {
        return jws.getBody().get("type").equals(JwtTokenType.ACCESS_TOKEN.getTokenType());
    }
}
