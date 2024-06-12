package crux.crux_server.config.login.exception;

import crux.crux_server.global.exception.CustomException;
import crux.crux_server.global.exception.ErrorCode;

public class LoginException extends CustomException {
    protected LoginException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static class LoginFailedException extends LoginException {
        public LoginFailedException() {
            super(ErrorCode.LOGIN_FAILED);
        }
    }
}
