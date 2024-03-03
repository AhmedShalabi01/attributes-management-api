package com.msa.attributesmanagementapi.exceptionhandlers;


import com.msa.attributesmanagementapi.exceptionhandlers.responsebodies.ConstraintViolationExceptionResponseBody;
import com.msa.attributesmanagementapi.exceptionhandlers.responsebodies.DuplicateKeyExceptionResponseBody;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ConstraintViolationExceptionResponseBody>
    handleFieldsValidationException(ConstraintViolationException exception) {

        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        ConstraintViolationExceptionResponseBody body =
                new ConstraintViolationExceptionResponseBody(status, exception);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<DuplicateKeyExceptionResponseBody>
    handleFieldsDuplicateKeyException() {

        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        DuplicateKeyExceptionResponseBody body =
                new DuplicateKeyExceptionResponseBody(status);

        return new ResponseEntity<>(body, status);
    }
}
