package crux.crux_server.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    // Basic HTTP Status Code
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // 1000: Authentication
    INVALID_VERIFICATION_CODE(1000, "인증 코드가 유효하지 않습니다"),
    VERIFICATION_CODE_NOT_FOUND(1001, "인증 코드를 찾을 수 없습니다"),
    LOGIN_FAILED(1002, "로그인 실패"),

    // 1001: Member
    MEMBER_NOT_FOUND(1001, "멤버를 찾을 수 없습니다"),


    ;

    private final int code;
    private final String message;
}
