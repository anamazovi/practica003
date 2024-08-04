package es.cic.curso.practica003.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.practica003.exception.ExpedienteNotFoundException;
import es.cic.curso.practica003.model.Expediente;
import es.cic.curso.practica003.service.ExpedienteService;

@RestController
@RequestMapping("/api/expedientes")
public class ExpedienteController {

    @Autowired
    private ExpedienteService expedienteService;

    //Devuelve una lista de todos los expedientes
    @GetMapping ("/api/expedientes")
    public List<Expediente> getAllExpedientes() {
        return expedienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expediente> getExpedienteById(@PathVariable Long id) {
        Expediente expediente = expedienteService.findById(id);
        if (expediente == null) {
            throw new ExpedienteNotFoundException("Expediente not found with id: " + id);
        }
        return ResponseEntity.ok(expediente);
    }


    //Crea un nuevo expediente que es Expediente expediente y lo devuelve
    @PostMapping ("/api/expedientes")
    public Expediente createExpediente(@RequestBody Expediente expediente) {
        return expedienteService.save(expediente);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Expediente> updateExpediente(@PathVariable Long id, @RequestBody Expediente expedienteDetails) {
        Expediente expediente = expedienteService.findById(id);
        if (expediente == null) {
            throw new ExpedienteNotFoundException("Expediente not found with id: " + id);
        }

        expediente.setNombre(expedienteDetails.getNombre());
        expediente.setDocumentos(expedienteDetails.getDocumentos());

        final Expediente updatedExpediente = expedienteService.save(expediente);
        return ResponseEntity.ok(updatedExpediente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpediente(@PathVariable Long id) {
        Expediente expediente = expedienteService.findById(id);
        if (expediente == null) {
            throw new ExpedienteNotFoundException("Expediente not found with id: " + id);
        }

        expedienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
