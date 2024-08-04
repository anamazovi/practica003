package es.cic.curso.practica003.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.practica003.model.Expediente;
import es.cic.curso.practica003.repository.ExpedienteRepository;



/*
 * @Service: Indica que esta clase es un servicio en Spring, responsable 
 * de la lógica de negocio.
 */
@Service
@Transactional
public class ExpedienteService {

    /*
     * @Autowired: Inyecta una instancia del repositorio 
     * DocumentoRepository en el servicio.
     */
    @Autowired
    private ExpedienteRepository expedienteRepository;

    /*
     * Métodos del servicio:
     * findAll(): Devuelve una lista de todas las entidades.
     * findById(Long id): Devuelve una entidad por su ID.
     * save(Object entity): Guarda una entidad en la base de datos.
     * deleteById(Long id): Elimina una entidad por su ID.
     */

    // Obtener todos los Documentos
    @Transactional
    public List<Expediente> findAll() {
        return expedienteRepository.findAll();
    }

    // Leer Documento por ID: buscamos documento en la bbdd por el id que es Long id y devuellve un Optiona<Documento> que lo contiene si lo encuentra
    @Transactional (readOnly = true)
    public Expediente findById(Long id) {
        return expedienteRepository.findById(id).orElse(null);
    }

    // Actualizar o crear Documento
    public Expediente save(Expediente expediente) {
        return expedienteRepository.save(expediente);
    }

    // Borrar Documento
    public void deleteById(Long id) {
        expedienteRepository.deleteById(id);
    }

  
}