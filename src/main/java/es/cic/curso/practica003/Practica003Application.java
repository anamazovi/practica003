package es.cic.curso.practica003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Practica003Application {

	public static void main(String[] args) {
		SpringApplication.run(Practica003Application.class, args);
	}

	// @Bean
	// public CommandLineRunner commandLineRunner(ExpedienteRepository expedienteRepository) {
	// 	return args -> {

	// 		Expediente expediente = new Expediente(); 
	// 		expediente.setNombre("Juan");

	// 		expedienteRepository.save(expediente);

	// 	};
	// }


}
