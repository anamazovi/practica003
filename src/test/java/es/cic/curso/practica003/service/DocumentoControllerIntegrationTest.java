package es.cic.curso.practica003.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso.practica003.model.Documento;
import es.cic.curso.practica003.model.Expediente;
import es.cic.curso.practica003.repository.DocumentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@SpringBootTest
public class DocumentoControllerIntegrationTest {


    @Autowired
    private DocumentoRepository documentoRepository;

	@PersistenceContext
	private EntityManager em;
    
	@Autowired
	private DocumentoService documentoService;

    @Autowired
    private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

    Documento documento;

    @BeforeEach
    public void setUp() {
        documento = new Documento();
        documento.setTitulo("Test Documento");
        documento.setContenido("Contenido de prueba");

        Expediente expediente = new Expediente();
        expediente.setNombre("Expediente Test");
        documento.setExpediente(expediente);

        documento = documentoRepository.save(documento);
        documentoRepository.flush();
    }

    @AfterEach
    public void tearDown() {
       
    }

    @Test
	void testListar() throws Exception {
		List<Documento> res = documentoService.listar();

		assertTrue(res.size() >= 1, "No existe al menos el registro que yo quería");
	}


	@Test
	void testActualizar () throws Exception {
		Documento documentoLeido = new Documento();
		documentoLeido.setId(documento.getId());
		documentoLeido.setTitulo("Ninguno");

		documentoService.Actualizar(documentoLeido);
	}


	@Test
	void testLeer() throws Exception {
		mvcResult = mockMvc.perform(get("/api/documento/{1}", documento.getId())
			.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(documento.getId()))
			.andReturn();

		String body = mvcResult.getResponse().getContentAsString();
		Documento documentoResultado = objectMapper.readValue(body, Documento.class);

		assertTrue(documentoResultado.getExpediente().size() >= 1);
	}
}