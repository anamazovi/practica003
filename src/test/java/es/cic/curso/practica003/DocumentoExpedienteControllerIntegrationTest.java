package es.cic.curso.practica003;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso.practica003.model.Expediente;
import es.cic.curso.practica003.repository.ExpedienteRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DocumentoExpedienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Expediente expediente;

    @BeforeEach
    void setUp() {
        expediente = new Expediente();
        expediente.setNombre("Expediente de prueba");
        expediente = expedienteRepository.save(expediente);
    }

    @Test
    void testGetAllExpedientes() throws Exception {
        mockMvc.perform(get("/api/expedientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateExpediente() throws Exception {
        Expediente expediente = new Expediente();
        expediente.setNombre("Nuevo expediente");

        MvcResult result = mockMvc.perform(post("/api/expedientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expediente)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Expediente createdExpediente = objectMapper.readValue(content, Expediente.class);

        assertThat(createdExpediente.getId()).isNotNull();
        assertThat(createdExpediente.getNombre()).isEqualTo("Nuevo expediente");
    }

    @Test
    void testGetExpedienteById() throws Exception {
        mockMvc.perform(get("/api/expedientes/{id}", expediente.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Expediente de prueba"));
    }

    @Test
    void testUpdateExpediente() throws Exception {
        Expediente updatedExpediente = new Expediente();
        updatedExpediente.setNombre("Expediente actualizado");

        mockMvc.perform(put("/api/expedientes/{id}", expediente.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedExpediente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Expediente actualizado"));
    }

    @Test
    void testDeleteExpediente() throws Exception {
        mockMvc.perform(delete("/api/expedientes/{id}", expediente.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertThat(expedienteRepository.findById(expediente.getId())).isEmpty();
    }
}
