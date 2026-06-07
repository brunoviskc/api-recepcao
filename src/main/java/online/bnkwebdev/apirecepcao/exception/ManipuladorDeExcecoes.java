package online.bnkwebdev.apirecepcao.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ManipuladorDeExcecoes {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manipularRecursoNaoEncontrado(RecursoNaoEncontradoException ex, HttpServletRequest request) {

        // Montando um JSON de erro
        Map<String, Object> corpoDoErro = new LinkedHashMap<>();
        corpoDoErro.put("timestamp", LocalDateTime.now());
        corpoDoErro.put("status", HttpStatus.NOT_FOUND.value());
        corpoDoErro.put("erro", "Não Encontrado");
        corpoDoErro.put("mensagem", ex.getMessage());
        corpoDoErro.put("caminho", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpoDoErro);
    }
}