package es.cic.curso.practica003.controller;

import java.util.List;
import java.util.Optional;

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
import es.cic.curso.practica003.model.Expediente;
import es.cic.curso.practica003.service.DocumentoService;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    //Devuelve una lista de todos los documentos
    @GetMapping ("/documentos")
    public List<Documento> getAllDocumentos() {
        return documentoService.listar();
    }

    @GetMapping("/{id}")
    public Documento leer(@PathVariable("id") long id)  {
        return documentoService.leer(id);
    }



    //Crea un nuevo documento que es Documento documento y lo devuelve
    @PostMapping ("/api/documentos")
    public Documento crearDocumento(@RequestBody Documento documento) {
        return documentoService.crearDocumento(documento);
    }

    /* 
    @PutMapping("/{id}")
    public ResponseEntity<Documento> actualizar(@PathVariable Long id, @RequestBody Documento documentoDetails) {
        Optional<Documento> optionalDocumento = documentoService.actualizar(id, documentoDetails);
        return optionalDocumento.map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarDocumento(@PathVariable Long id) {
        documentoService.borrarDocumento(id);
        return ResponseEntity.noContent().build();
    }
}
