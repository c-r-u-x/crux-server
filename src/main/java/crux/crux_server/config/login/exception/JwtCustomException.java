package crux.crux_server.config.login.exception;

import crux.crux_server.global.exception.CustomException;
import crux.crux_server.global.exception.ErrorCode;

public class JwtCustomException extends CustomException {
    protected JwtCustomException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static class JwtExpiredException extends JwtCustomException {
        public JwtExpiredException() {
            super(ErrorCode.JWT_EXPIRED);
        }
    }

    public static class JwtInvalidException extends JwtCustomException {
        public JwtInvalidException() {
            super(ErrorCode.JWT_INVALID);
        }
    }
}
