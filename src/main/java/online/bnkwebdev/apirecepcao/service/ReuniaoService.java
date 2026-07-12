package online.bnkwebdev.apirecepcao.service;

import online.bnkwebdev.apirecepcao.exception.RecursoNaoEncontradoException;
import online.bnkwebdev.apirecepcao.model.Reuniao;
import online.bnkwebdev.apirecepcao.repository.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
        // 1. Captura o momento exato de "agora"
        LocalDate dataHoje = LocalDate.now();
        LocalTime horaAgora = LocalTime.now();

        // 2. Regra 1: A data solicitada é mais antiga que hoje? (Ontem, mês passado, etc)
        if (reuniao.getDataReuniao().isBefore(dataHoje)) {
            throw new IllegalArgumentException("Data inválida: Não é possível realizar agendamentos no passado.");
        }

        // 3. Regra 2: A data é hoje, mas a hora solicitada já passou?
        if (reuniao.getDataReuniao().isEqual(dataHoje) && reuniao.getHoraReuniao().isBefore(horaAgora)) {
            throw new IllegalArgumentException("Horário inválido: Este horário já passou no dia de hoje.");
        }

        // 4. Regra 3: Trava de segurança contra horários duplicados no banco
        boolean horarioOcupado = repository.existsByDataReuniaoAndHoraReuniao(
                reuniao.getDataReuniao(),
                reuniao.getHoraReuniao()
        );

        if (horarioOcupado) {
            throw new IllegalArgumentException("Horário indisponível: Já existe uma reunião agendada para este dia e horário exatos.");
        }

        // 5. Se sobreviveu a todas as regras, salva no banco!
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

    @Scheduled(cron = "0 0 2 * * * ")
    public void limparAgendaAntigaAutomatica() {
        //Descobre que dia goi há exatos 7 dias atrás
        LocalDate limiteUmaSemanaAtras = LocalDate.now().minusDays(7);

        //Manda o banco deletar tudo que for anterior a essa data
        repository.deleteByDataReuniaoBefore(limiteUmaSemanaAtras);

        //Imprime no console apenas para termos um log de que o robô funcionou
        System.out.println("[FAXINA CONCLUÍDA] Reuniões anteriores a " + limiteUmaSemanaAtras + " foram deletadas do PostgreSQL.");
    }
}
