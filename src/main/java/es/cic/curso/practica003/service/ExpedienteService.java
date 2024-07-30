package es.cic.curso.practica003.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.practica003.exception.DocumentoExpedienteException;
import es.cic.curso.practica003.model.Expediente;
import es.cic.curso.practica003.repository.ExpedienteRepository;

@Service
public class ExpedienteService {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    public List<Expediente> getAllExpedientes() {
        return expedienteRepository.findAll();
    }

    public Expediente getExpedienteById(Long id) {
        return expedienteRepository.findById(id)
                .orElseThrow(() -> new DocumentoExpedienteException("Expediente no encontrado con id: " + id));
    }

    public Expediente createExpediente(Expediente expediente) {
        return expedienteRepository.save(expediente);
    }

    public Expediente updateExpediente(Long id, Expediente expedienteDetails) {
        Expediente expediente = getExpedienteById(id);
        expediente.setNombre(expedienteDetails.getNombre());
        expediente.getDocumentos().clear();
        expediente.getDocumentos().addAll(expedienteDetails.getDocumentos());
        return expedienteRepository.save(expediente);
    }

    public void deleteExpediente(Long id) {
        Expediente expediente = getExpedienteById(id);
        expedienteRepository.delete(expediente);
    }
}
