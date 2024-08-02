package es.cic.curso.practica003.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DocumentoExpedienteException extends RuntimeException {
    public DocumentoExpedienteException () {
        private long id;


    }

    public DocumentoExpedienteException(String message) {
        super(message);
    }

    public DocumentoExpedienteException (String message, Throwable th) {
        super (message);
    }




}

