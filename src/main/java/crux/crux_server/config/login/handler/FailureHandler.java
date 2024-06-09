package crux.crux_server.config.login.handler;

import crux.crux_server.global.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 로그인 실패 핸들러
 */
@Component
@Slf4j
public class FailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 로그인 실패시 응답
        log.error("로그인 실패: {}", exception.getMessage());
        log.error("로그인 실패: {}", exception.getCause());
        log.error("요청: {}", request.getRequestURI());

        response.sendError(ErrorCode.LOGIN_FAILED.getCode(), ErrorCode.LOGIN_FAILED.getMessage());
        response.getWriter().write(ErrorCode.LOGIN_FAILED.getMessage());
    }
}
