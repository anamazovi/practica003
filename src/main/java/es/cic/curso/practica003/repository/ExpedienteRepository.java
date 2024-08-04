

package es.cic.curso.practica003.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.cic.curso.practica003.model.Expediente;

@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {
}

