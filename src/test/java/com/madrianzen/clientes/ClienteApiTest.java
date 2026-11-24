package com.madrianzen.clientes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteApiTest {

    @Autowired
    MockMvc mvc;

    @Test
    void creaYListaCliente() throws Exception {
        String json = """
                {"nombre":"Ana","apellido":"Torres","fechaNacimiento":"1990-05-17","nacionalidad":"Peruana"}
                """;
        mvc.perform(post("/api/clientes").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        mvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ana"));
    }

    @Test
    void rechazaClienteInvalido() throws Exception {
        String json = """
                {"nombre":"","apellido":"Torres","fechaNacimiento":"2999-01-01","nacionalidad":""}
                """;
        mvc.perform(post("/api/clientes").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nombre").exists())
                .andExpect(jsonPath("$.fechaNacimiento").exists())
                .andExpect(jsonPath("$.nacionalidad").exists());
    }

    @Test
    void paginaWebCarga() throws Exception {
        mvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Registro de clientes")));
    }
}
