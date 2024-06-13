package crux.crux_server.global.exception;

public class HttpException extends CustomException {
    protected HttpException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static class BadRequestException extends HttpException {
        public BadRequestException() {
            super(ErrorCode.BAD_REQUEST);
        }
    }

    public static class UnauthorizedException extends HttpException {
        public UnauthorizedException() {
            super(ErrorCode.UNAUTHORIZED);
        }
    }

    public static class ForbiddenException extends HttpException {
        public ForbiddenException() {
            super(ErrorCode.FORBIDDEN);
        }
    }

    public static class NotFoundException extends HttpException {
        public NotFoundException() {
            super(ErrorCode.NOT_FOUND);
        }
    }

    public static class ConflictException extends HttpException {
        public ConflictException() {
            super(ErrorCode.CONFLICT);
        }
    }

    public static class InternalServerException extends HttpException {
        public InternalServerException() {
            super(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
