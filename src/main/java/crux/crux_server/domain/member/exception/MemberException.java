package crux.crux_server.domain.member.exception;


import crux.crux_server.global.exception.CustomException;
import crux.crux_server.global.exception.ErrorCode;

public class MemberException extends CustomException {
    protected MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}