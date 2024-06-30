package crux.crux_server.config.login.exception;

import crux.crux_server.global.exception.CustomException;
import crux.crux_server.global.exception.ErrorCode;

import java.util.Map;

public class JwtCustomException extends CustomException {
    protected JwtCustomException(ErrorCode errorCode) {
        super(errorCode);
    }
    protected JwtCustomException(ErrorCode errorCode, Map<String, String> property) {
        super(errorCode, property);
    }

    public static class JwtExpiredException extends JwtCustomException {
        public JwtExpiredException() {
            super(ErrorCode.JWT_EXPIRED);
        }
        public JwtExpiredException(Map<String, String> property) {
            super(ErrorCode.JWT_EXPIRED, property);
        }
    }

    public static class JwtInvalidException extends JwtCustomException {
        public JwtInvalidException() {
            super(ErrorCode.JWT_INVALID);
        }
        public JwtInvalidException(Map<String, String> property) {
            super(ErrorCode.JWT_INVALID, property);
        }
    }
}
