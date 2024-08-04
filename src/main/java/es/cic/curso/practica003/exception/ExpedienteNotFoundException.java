package es.cic.curso.practica003.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExpedienteNotFoundException extends RuntimeException {

    public ExpedienteNotFoundException() {
        super("Expediente not found");
    }

    public ExpedienteNotFoundException(String message) {
        super(message);
    }

    public ExpedienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

