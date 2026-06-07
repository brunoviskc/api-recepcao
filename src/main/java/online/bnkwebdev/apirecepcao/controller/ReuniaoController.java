package online.bnkwebdev.apirecepcao.controller;

import jakarta.validation.Valid;
import online.bnkwebdev.apirecepcao.model.Reuniao;
import online.bnkwebdev.apirecepcao.service.ReuniaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reunioes") // <-- Estratégia de Versionamento de API aplicada aqui!
public class ReuniaoController {

    @Autowired
    private ReuniaoService service; // <-- Injetando a nova Camada de Serviço

    @GetMapping
    public ResponseEntity<List<Reuniao>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reuniao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Reuniao> criarReuniao(@Valid @RequestBody Reuniao reuniao) {
        Reuniao novaReuniao = service.salvar(reuniao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReuniao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reuniao> atualizarReuniao(@PathVariable Long id, @Valid @RequestBody Reuniao reuniaoAtualizada) {
        Reuniao reuniaoSalva = service.atualizar(id, reuniaoAtualizada);
        return ResponseEntity.ok(reuniaoSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReuniao(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
