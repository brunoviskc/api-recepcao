package online.bnkwebdev.apirecepcao.repository;

import online.bnkwebdev.apirecepcao.model.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ReuniaoRepository extends JpaRepository<Reuniao, Long> {

    // O Spring traduz isso para: SELECT COUNT(*) > 0 FROM reuniao WHERE data_reuniao = ? AND hora_reuniao = ?
    boolean existsByDataReuniaoAndHoraReuniao(LocalDate dataReuniao, LocalTime horaReuniao);

    // Comando: Deleta tudo que tiver a data "antes de" (Before) a data informada
    @Transactional
    void deleteByDataReuniaoBefore(LocalDate dataLimite);

}
