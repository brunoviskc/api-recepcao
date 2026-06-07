package online.bnkwebdev.apirecepcao.repository;

import online.bnkwebdev.apirecepcao.model.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReuniaoRepository extends JpaRepository<Reuniao, Long> {
    
}
