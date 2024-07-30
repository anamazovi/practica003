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

import es.cic.curso.practica003.model.Documento;
import es.cic.curso.practica003.service.DocumentoService;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @GetMapping
    public List<Documento> getAllDocumentos() {
        return documentoService.getAllDocumentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> getDocumentoById(@PathVariable Long id) {
        Documento documento = documentoService.getDocumentoById(id);
        return ResponseEntity.ok(documento);
    }

    @PostMapping
    public Documento createDocumento(@RequestBody Documento documento) {
        return documentoService.createDocumento(documento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> updateDocumento(@PathVariable Long id, @RequestBody Documento documentoDetails) {
        Documento updatedDocumento = documentoService.updateDocumento(id, documentoDetails);
        return ResponseEntity.ok(updatedDocumento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumento(@PathVariable Long id) {
        documentoService.deleteDocumento(id);
        return ResponseEntity.noContent().build();
    }
}
