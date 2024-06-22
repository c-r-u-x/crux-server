package crux.crux_server.global.exception;

import crux.crux_server.config.login.exception.JwtCustomException;
import crux.crux_server.domain.member.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 기존 유효성 검사 예외 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        final ErrorResponse response = ErrorResponse.from(new HttpException.BadRequestException(errors));
        return ResponseEntity.badRequest().body(response);

    }

    // 새로운 타입 미스매치 예외 핸들러
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = ex.getName();
        String errorMessage = "Invalid value for field: " + fieldName;
        errors.put(fieldName, errorMessage);

        final ErrorResponse response = ErrorResponse.from(new HttpException.BadRequestException(errors));
        return ResponseEntity.badRequest().body(response);
    }

    // 잘못된 형식의 데이터 전송
    @ExceptionHandler(HttpMessageConversionException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageConversionException(final HttpMessageConversionException e) {
        log.error("HttpMessageConversionException", e);
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Invalid request body");

        final ErrorResponse response = ErrorResponse.from(new HttpException.BadRequestException(errors));
        return ResponseEntity.badRequest().body(response);
    }


    /** 여기부터는 CustomException 처리 핸들러 */

    // 400 Bad Request
    @ExceptionHandler({
            JwtCustomException.JwtExpiredException.class,
            JwtCustomException.JwtInvalidException.class,
    })
    public ResponseEntity<ErrorResponse> handleGlobalBadRequestException(final CustomException e) {
        return ResponseEntity.status(BAD_REQUEST).body(ErrorResponse.from(e));
    }

//    // 401 Unauthorized
//    @ExceptionHandler({
//            // 인증 오류 추가
//    })
//    public ResponseEntity<ErrorResponse> handleGlobalUnauthorizedException(final CustomException e) {
//        return ResponseEntity.status(UNAUTHORIZED).body(ErrorResponse.from(e));
//    }
//
//    // 403 Forbidden
//    @ExceptionHandler({
//            // 권한 오류 추가
//    })
//    public ResponseEntity<ErrorResponse> handleGlobalForbiddenException(final CustomException e) {
//        return ResponseEntity.status(FORBIDDEN).body(ErrorResponse.from(e));
//    }

    // 404 Not Found
    @ExceptionHandler({
            MemberException.MemberNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleGlobalNotFoundException(final CustomException e) {
        return ResponseEntity.status(NOT_FOUND).body(ErrorResponse.from(e));
    }

//    // 409 Conflict
//    @ExceptionHandler({
//            // 중복 오류 추가
//    })
//    public ResponseEntity<ErrorResponse> handleGlobalConflictException(final CustomException e) {
//        return ResponseEntity.status(CONFLICT).body(ErrorResponse.from(e));
//    }
//
//    // 500 Internal Server Error
//    @ExceptionHandler({
//            // 서버 오류 추가
//    })
//    public ResponseEntity<ErrorResponse> handleGlobalInternalServerException(final CustomException e) {
//        log.error(e.getErrorInfoLog());
//        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ErrorResponse.from(e));
//    }
}
