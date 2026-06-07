package online.bnkwebdev.apirecepcao.service;

import online.bnkwebdev.apirecepcao.exception.RecursoNaoEncontradoException;
import online.bnkwebdev.apirecepcao.model.Reuniao;
import online.bnkwebdev.apirecepcao.repository.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReuniaoService {

    @Autowired
    private ReuniaoRepository repository;

    public List<Reuniao> listarTodos() {
        return repository.findAll();
    }

    public Reuniao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Reunião não encontrada com o ID: " + id));
    }

    public Reuniao salvar(Reuniao reuniao) {
        return repository.save(reuniao);
    }

    public Reuniao atualizar(Long id, Reuniao reuniaoAtualizada) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Impossível atualizar. Reunião não encontrada com o ID: " + id);
        }
        reuniaoAtualizada.setId(id);
        return repository.save(reuniaoAtualizada);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Impossível deletar. Reunião não encontrada com o ID: " + id);
        }
        repository.deleteById(id);
    }
}
