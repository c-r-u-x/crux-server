package crux.crux_server.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServer(final Exception e) {
        final CustomException customException = CustomException.from(
            ErrorCode.INTERNAL_SERVER_ERROR);

        log.error(e.toString());

        return ResponseEntity.internalServerError().body(ErrorResponse.from(customException));
    }
}
