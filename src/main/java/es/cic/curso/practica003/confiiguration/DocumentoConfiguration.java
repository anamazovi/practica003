package es.cic.curso.practica003.confiiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("los.properties")

public class DocumentoConfiguration {
    @Value("${practica003.documento.valor}")
    private long valor;

    public long getValor() {
        return valor;
    }


}