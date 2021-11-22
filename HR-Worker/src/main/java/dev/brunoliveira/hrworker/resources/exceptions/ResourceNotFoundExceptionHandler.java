package dev.brunoliveira.hrworker.resources.exceptions;

import dev.brunoliveira.hrworker.service.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ResourceNotFoundExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError errorBody = new StandardError();
        errorBody.setTimestamp(Instant.now());
        errorBody.setStatus(status.value());
        errorBody.setError("Resource not found.");
        errorBody.setMessage(exception.getMessage());
        errorBody.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(errorBody);
    }
}
