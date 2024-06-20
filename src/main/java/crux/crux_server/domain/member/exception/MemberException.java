package crux.crux_server.domain.member.exception;


import crux.crux_server.global.exception.CustomException;
import crux.crux_server.global.exception.ErrorCode;

import java.util.Map;

public class MemberException extends CustomException {
    protected MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
    protected MemberException(ErrorCode errorCode, Map<String, String> property) {
        super(errorCode, property);
    }

    public static class MemberNotFoundException extends MemberException {
        public MemberNotFoundException() {
            super(ErrorCode.MEMBER_NOT_FOUND);
        }
        public MemberNotFoundException(Map<String, String> property) {
            super(ErrorCode.MEMBER_NOT_FOUND, property);
        }
    }
}
