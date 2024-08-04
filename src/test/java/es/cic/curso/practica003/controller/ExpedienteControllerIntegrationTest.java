package es.cic.curso.practica003.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso.practica003.model.Expediente;
import es.cic.curso.practica003.repository.ExpedienteRepository;
import es.cic.curso.practica003.service.ExpedienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@SpringBootTest
public class ExpedienteControllerIntegrationTest {


    @Autowired
    private ExpedienteRepository expedienteRepository;

	@PersistenceContext
	private EntityManager em;
    
	@Autowired
	private ExpedienteService expedienteService;

    @Autowired
    private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

    private Expediente expediente1;
    private Expediente expediente2;


    @BeforeEach
    public void setUp() {
        expediente1 = new Expediente();
        expediente1.setNombre("Expediente 1");

        expediente2 = new Expediente();
        expediente2.setNombre("Expediente 2");

		expedienteRepository.save(expediente1);
        expedienteRepository.save(expediente2);
    }

    @AfterEach
    public void tearDown() {
        expedienteRepository.deleteAll();
    }

    @Test
    public void testGetAllExpedientes() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/expedientes"))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        List<Expediente> expedientes = Arrays.asList(objectMapper.readValue(result.getResponse().getContentAsString(), Expediente[].class));
        assertEquals(2, expedientes.size());
    }

    @Test
    public void testGetExpedienteById_NotFound() throws Exception {
        Long id = 999L; // Un ID que sabemos que no existe

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/expedientes/{id}", id))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    public void testGetExpedienteById_Found() throws Exception {
        Long id = expediente1.getId();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/expedientes/{id}", id))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Expediente foundExpediente = objectMapper.readValue(result.getResponse().getContentAsString(), Expediente.class);
        assertNotNull(foundExpediente);
        assertEquals("Expediente 1", foundExpediente.getNombre());
    }

    @Test
    public void testCreateExpediente() throws Exception {
        Expediente newExpediente = new Expediente();
        newExpediente.setNombre("Expediente 3");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/expedientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newExpediente)))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Expediente createdExpediente = objectMapper.readValue(result.getResponse().getContentAsString(), Expediente.class);
        assertNotNull(createdExpediente);
        assertEquals("Expediente 3", createdExpediente.getNombre());
    }

    @Test
    public void testUpdateExpediente_NotFound() throws Exception {
        Long id = 999L; // Un ID que sabemos que no existe
        Expediente expedienteDetails = new Expediente();
        expedienteDetails.setNombre("Updated Expediente");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/expedientes/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(expedienteDetails)))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    public void testUpdateExpediente_Found() throws Exception {
        Long id = expediente1.getId();

        Expediente expedienteDetails = new Expediente();
        expedienteDetails.setNombre("Updated Expediente");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/expedientes/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(expedienteDetails)))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        Expediente updatedExpediente = objectMapper.readValue(result.getResponse().getContentAsString(), Expediente.class);
        assertNotNull(updatedExpediente);
        assertEquals("Updated Expediente", updatedExpediente.getNombre());
    }

    @Test
    public void testDeleteExpediente_NotFound() throws Exception {
        Long id = 999L; // Un ID que sabemos que no existe

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/expedientes/{id}", id))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    public void testDeleteExpediente_Found() throws Exception {
        Long id = expediente1.getId();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/expedientes/{id}", id))
                .andReturn();

        assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }
}