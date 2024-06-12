package crux.crux_server.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 환경 변수를 application.yml 에서 가져오거나
 * 스프링 빈을 등록하기 위한 객체
 **/
@Getter
@Component
public class EnvBean {
    @Value("${jwt.secret}")
    private String jwtSecretKey;
    @Value("${jwt.access-token-time}")
    private long accessTokenTime;
    @Value("${jwt.refresh-token-time}")
    private long refreshTokenTime;
}
