package online.bnkwebdev.apirecepcao.controller;

import jakarta.validation.Valid;
import online.bnkwebdev.apirecepcao.exception.RecursoNaoEncontradoException;
import online.bnkwebdev.apirecepcao.model.Reuniao;
import online.bnkwebdev.apirecepcao.repository.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reunioes")
public class ReuniaoController {

    @Autowired
    private ReuniaoRepository repository;

    //GET: Lista todas as reuniões
    @GetMapping
    public List<Reuniao> listarTodos(){
        return repository.findAll();
    }

    //GET: Busca uma reunião pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Reuniao> buscarPorId(@PathVariable Long id){
        Reuniao reuniao = repository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Reunião não encontrada com ID:" + id));
        return ResponseEntity.ok(reuniao);
    }

    //POST: Adicione uma nova reunião
    @PostMapping
    public ResponseEntity<Reuniao> criarReuniao(@Valid @RequestBody Reuniao reuniao){
        Reuniao novaReuniao = repository.save(reuniao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReuniao); // Retorna 201 Created
    }

    //PUT: Atualize uma reunião existente
    @PutMapping("/{id}")
    public ResponseEntity<Reuniao> atualizarReuniao(@PathVariable Long id, @Valid @RequestBody Reuniao reuniaoAtualizada){
        if(!repository.existsById(id)){
            throw new RecursoNaoEncontradoException("Impossível atualizar. Reunião não  encontrada com ID:" + id);
        }
        reuniaoAtualizada.setId(id); // Trava o ID para não criar um novo registro sem querer
        Reuniao reuniaoSalva = repository.save(reuniaoAtualizada);
        return ResponseEntity.ok(reuniaoSalva); // Retorna 200 OK
    }

    //DELETE: Excluir uma reunião
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReuniao(@PathVariable Long id){
        if(!repository.existsById(id)){
            throw new RecursoNaoEncontradoException("Impossível deletar. Reunião não encontrada com ID:" + id);
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 Content
    }
}
