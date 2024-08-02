package es.cic.curso.practica003.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Documento {

    /* 
    * @Id: Indica que el campo id es la clave primaria de la entidad.
    * @GeneratedValue(strategy = GenerationType.AUTO): Indica que el valor del 
    * campo id será generado automáticamente por la base de datos. 
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String contenido;

    /*
     * @ManyToOne: Indica una relación de muchos a uno. Muchos Documento pueden 
     * estar asociados a un Expediente.
     * @JoinColumn(name = "expediente_id"): Especifica el nombre de la columna en 
     * la base de datos que se utilizará para unir la tabla Documento con la tabla Expediente.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "expediente_id")
    @JsonIgnore
    private Expediente expediente;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    
}