package edu.esiea.tp_sb.infra;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import org.postgresql.util.PSQLState;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> notFound(NotFoundException ex, HttpServletRequest req) {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Not found");
        pd.setDetail(ex.getMessage());
        pd.setInstance(URI.create(req.getRequestURI()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pd);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Validation Error");

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        pd.setProperty("errors", errors);
        return ResponseEntity.badRequest().body(pd);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        Throwable root = ex.getRootCause();
        String message = root != null ? root.getMessage() : ex.getMessage();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemDetail pd = ProblemDetail.forStatus(status);
        pd.setInstance(URI.create(req.getRequestURI()));
        // Check if it's a duplicate key
        if (root instanceof PSQLException pgEx) {
            
            if (PSQLState.UNIQUE_VIOLATION.getState().equals(pgEx.getSQLState())) {
                status = HttpStatus.CONFLICT;
                pd.setStatus(HttpStatus.CONFLICT);
                pd.setTitle("Duplicate Key Error");
                pd.setDetail("A record with this unique value already exists.");
            } else {
                pd.setTitle("Database Error");
                pd.setDetail(message);
            }
        } else {
            pd.setTitle("Database Error");
            pd.setDetail(message);
        }

        return ResponseEntity.status(status).body(pd);
    }



    @ExceptionHandler(Throwable.class)    
    public ResponseEntity<ProblemDetail> handleDefaultException(Throwable ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Internal Server Error");

        return ResponseEntity.internalServerError().body(pd);
    }

}