package es.cic.curso.practica003.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.practica003.exception.DocumentoExpedienteException;
import es.cic.curso.practica003.model.Documento;
import es.cic.curso.practica003.repository.DocumentoRepository;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public List<Documento> getAllDocumentos() {
        return documentoRepository.findAll();
    }

    public Documento getDocumentoById(Long id) {
        return documentoRepository.findById(id)
                .orElseThrow(() -> new DocumentoExpedienteException("Documento no encontrado con id: " + id));
    }

    public Documento createDocumento(Documento documento) {
        return documentoRepository.save(documento);
    }

    public Documento updateDocumento(Long id, Documento documentoDetails) {
        Documento documento = getDocumentoById(id);
        documento.setNombre(documentoDetails.getNombre());
        documento.setExpediente(documentoDetails.getExpediente());
        return documentoRepository.save(documento);
    }

    public void deleteDocumento(Long id) {
        Documento documento = getDocumentoById(id);
        documentoRepository.delete(documento);
    }
}
