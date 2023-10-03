package br.com.lanchonete.repository.postgres.repository;

import br.com.lanchonete.repository.postgres.entity.SequenceNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPostgresSequenceNumberRepository extends JpaRepository<SequenceNumber, Long> {

}
