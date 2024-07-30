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

import es.cic.curso.practica003.model.Expediente;
import es.cic.curso.practica003.service.ExpedienteService;

@RestController
@RequestMapping("/api/expedientes")
public class ExpedienteController {

    @Autowired
    private ExpedienteService expedienteService;

    @GetMapping
    public List<Expediente> getAllExpedientes() {
        return expedienteService.getAllExpedientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expediente> getExpedienteById(@PathVariable Long id) {
        Expediente expediente = expedienteService.getExpedienteById(id);
        return ResponseEntity.ok(expediente);
    }

    @PostMapping
    public Expediente createExpediente(@RequestBody Expediente expediente) {
        return expedienteService.createExpediente(expediente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expediente> updateExpediente(@PathVariable Long id, @RequestBody Expediente expedienteDetails) {
        Expediente updatedExpediente = expedienteService.updateExpediente(id, expedienteDetails);
        return ResponseEntity.ok(updatedExpediente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpediente(@PathVariable Long id) {
        expedienteService.deleteExpediente(id);
        return ResponseEntity.noContent().build();
    }
}