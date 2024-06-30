package crux.crux_server.config.login.exception;

import crux.crux_server.global.exception.CustomException;
import crux.crux_server.global.exception.ErrorCode;

import java.util.Map;

public class LoginException extends CustomException {
    protected LoginException(ErrorCode errorCode) {
        super(errorCode);
    }
    protected LoginException(ErrorCode errorCode, Map<String, String> property) {
        super(errorCode, property);
    }

    public static class LoginFailedException extends LoginException {
        public LoginFailedException() {
            super(ErrorCode.LOGIN_FAILED);
        }
        public LoginFailedException(Map<String, String> property) {
            super(ErrorCode.LOGIN_FAILED, property);
        }
    }
}
