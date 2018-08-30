package br.com.seniorsolution.estagiario.exceptions.handler.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.seniorsolution.estagiario.exceptions.DefaultException;
import br.com.seniorsolution.estagiario.model.dto.v1.ExceptionDTO;

@ControllerAdvice
public class ExceptionHandling {
    
    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ExceptionDTO> showmessage(DefaultException e) {
        ExceptionDTO eR = new ExceptionDTO();
        eR.setCode(HttpStatus.BAD_REQUEST.value());
        eR.setDescricao(e.getMessage());
        return new ResponseEntity<>(eR, HttpStatus.BAD_REQUEST);
    }
    
}
