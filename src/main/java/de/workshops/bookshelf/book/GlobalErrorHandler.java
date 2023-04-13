package de.workshops.bookshelf.book;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler({ConstraintViolationException.class, BookException.class})
    ResponseEntity<String> handleConstraintViolations(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
