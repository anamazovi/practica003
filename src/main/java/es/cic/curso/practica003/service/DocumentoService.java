package es.cic.curso.practica003.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.practica003.exception.DocumentoExpedienteException;
import es.cic.curso.practica003.model.Documento;
import es.cic.curso.practica003.repository.DocumentoRepository;



/*
 * @Service: Indica que esta clase es un servicio en Spring, responsable 
 * de la lógica de negocio.
 */
@Service
@Transactional
public class DocumentoService {

    /*
     * @Autowired: Inyecta una instancia del repositorio 
     * DocumentoRepository en el servicio.
     */
    @Autowired
    private DocumentoRepository documentoRepository;

    /*
     * Métodos del servicio:
     * findAll(): Devuelve una lista de todas las entidades.
     * findById(Long id): Devuelve una entidad por su ID.
     * save(Object entity): Guarda una entidad en la base de datos.
     * deleteById(Long id): Elimina una entidad por su ID.
     */

   // Crear Documento 
   public Documento crearDocumento(Documento documento) {
        return documentoRepository.save(documento);
    }

    // Leer Documento por ID: buscamos documento en la bbdd por el id que es Long id y devuellve un Optiona<Documento> que lo contiene si lo encuentra
    @Transactional (readOnly = true)
    public Documento leer (Long id) {
        return documentoRepository.findById(id).orElseThrow(() -> new DocumentoExpedienteException (String.format("No lo encontré el %d chaval@", id)));
    }

    // Actualizar Documento
    public void Actualizar(Documento documento) {
        documentoRepository.save(documento);
        
    }

    // Borrar Documento
    public void borrarDocumento(Long id) {
        documentoRepository.deleteById(id);
    }

    // Obtener todos los Documentos
    @Transactional
    public List<Documento> listar() {
        return documentoRepository.findAll();
    }
}