package com.ajouway.common.exception;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ErrorResponseBody> handleCustomException(HttpServletRequest request, CustomException ex) {
        CustomExceptionInfo customErrorInfo = ex.getCustomExceptionInfo();
        ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
                .detailStatusCode(customErrorInfo.getStatusCode())
                .message(customErrorInfo.getMessage())
                .build();

        return ResponseEntity
                .status(customErrorInfo.getStatusCode())
                .body(errorResponseBody);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleValidationExceptions(HttpServletRequest request, Exception ex) {
        return ResponseEntity
                .status(500)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(JwtInvalidException.class)
    public ResponseEntity<Object> handlerJwtInvalidException(JwtInvalidException ex){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorCode", "INVALID_JWT");
        errorResponse.put("errorMessage", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
