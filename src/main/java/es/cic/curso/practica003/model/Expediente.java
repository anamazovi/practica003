package es.cic.curso.practica003.model;

import java.util.List;

import org.hibernate.engine.jdbc.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Expediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size (max =10)
    private String nombre;


    /*
     * @OneToMany: Indica una relación de uno a muchos. Un Expediente puede 
     * tener muchos Documentos.
     * mappedBy = "expediente": Indica que la relación está mapeada por el campo 
     * expediente en la clase Documento.
     * cascade = CascadeType.ALL: Significa que cualquier operación de persistencia
     *  (guardar, actualizar, borrar) realizada en Expediente se aplicará también a los Documento asociados.
     * orphanRemoval = true: Indica que si se elimina un Documento de la lista documentos en un Expediente, 
     * el Documento también se eliminará de la base de datos.
     * private List<Documento> documentos: Declara una lista de Documento asociada a un Expediente
     */
    
    @OneToMany(mappedBy = "expediente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos;

    // Getters y setters
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<Documento> getDocumentos() {
        return documentos;
    }


    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }


}




