/*
 * @Repository: Indica que esta interfaz es un componente de repositorio en Spring, encargado 
 * de interactuar con la base de datos.
 * DocumentoRepository extends JpaRepository<Documento, Long>: JpaRepository: Es una interfaz 
 * de Spring Data JPA que proporciona métodos para realizar operaciones CRUD.
 */

package es.cic.curso.practica003.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.cic.curso.practica003.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
