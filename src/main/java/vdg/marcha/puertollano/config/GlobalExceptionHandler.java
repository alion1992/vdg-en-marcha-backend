package vdg.marcha.puertollano.config;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vdg.marcha.puertollano.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(
            RuntimeException ex) {

        return ResponseEntity.badRequest()
                .body(
                        ErrorResponse.builder()
                                .message(ex.getMessage())
                                .build()
                );
    }
}