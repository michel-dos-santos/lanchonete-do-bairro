package br.com.lanchonete.postgres.repository;

import br.com.lanchonete.postgres.entity.SequenceNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPostgresSequenceNumberRepository extends JpaRepository<SequenceNumber, Long> {

}
