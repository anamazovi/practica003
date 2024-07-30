package es.cic.curso.practica003.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.cic.curso.practica003.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
