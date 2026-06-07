package online.bnkwebdev.apirecepcao.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manipularErrosDeValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, Object> corpoDoErro = new LinkedHashMap<>();
        corpoDoErro.put("timestamp", LocalDateTime.now());
        corpoDoErro.put("status", HttpStatus.BAD_REQUEST.value());
        corpoDoErro.put("erro", "Erro de Validação nos Dados Enviados");

        // Vai varrer os erros e criar uma listinha de: "campo" -> "mensagem de erro"
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                erros.put(error.getField(), error.getDefaultMessage())
        );

        corpoDoErro.put("mensagens", erros);
        corpoDoErro.put("caminho", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoDoErro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> manipularErroDeLeitura(HttpMessageNotReadableException ex, HttpServletRequest request) {

        Map<String, Object> corpoDoErro = new LinkedHashMap<>();
        corpoDoErro.put("timestamp", LocalDateTime.now());
        corpoDoErro.put("status", HttpStatus.BAD_REQUEST.value());
        corpoDoErro.put("erro", "Formato de Dado Inválido");
        corpoDoErro.put("mensagem", "Erro na leitura do JSON. Verifique se os campos de data, hora ou números estão no formato correto.");
        corpoDoErro.put("caminho", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpoDoErro);
    }
}