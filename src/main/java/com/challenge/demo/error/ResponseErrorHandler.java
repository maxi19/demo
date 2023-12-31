package com.challenge.demo.error;
import com.challenge.demo.controller.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ResponseErrorHandler extends ResponseEntityExceptionHandler {




    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        logger.info(ex.getClass().getName());

        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        final ErrorResponse errorReponse = new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), errors);
        return handleExceptionInternal(ex, errorReponse, headers, errorReponse.getStatus(), request);

    }

    @ExceptionHandler({ExisteUsuarioException.class})
    public ResponseEntity<ErrorResponse> handle(ExisteUsuarioException e){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST,"",e.getMessage() ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TokenExpiradoException.class})
    public ResponseEntity<ErrorResponse> tokenExpirado(TokenExpiradoException e){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST,"",e.getMessage() ), HttpStatus.BAD_REQUEST);
    }
}
