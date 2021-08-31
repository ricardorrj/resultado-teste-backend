package com.ricardorrj.cadastroProcesso.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import com.ricardorrj.cadastroProcesso.service.PessoaService;
import com.ricardorrj.cadastroProcesso.service.ProcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PessoaResourceTest {

    @MockBean
    private PessoaService pessoaService;

    @MockBean
    private ProcessoService processoService;

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper objectMapper;


    private Pessoa cenarioTestes(){
        Pessoa pessoa = new Pessoa(1L, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25"));
        return pessoa;
    }


    @Test
    public void findByIdTest() throws Exception{

        when(this.pessoaService.findById(1L)).thenReturn( cenarioTestes() );

        mock.perform(get("/pessoa/1")
                        .content(objectMapper.writeValueAsString(Long.toString(1L)))
                        .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.id", is(1)))
                            .andExpect(jsonPath("$.nome", is("Rocky Balboa")))
                            .andExpect(jsonPath("$.cpf", is("88129054809")))
                            .andExpect(jsonPath("$.dataNascimento", is("1970-12-25")));

        verify(pessoaService).findById(1L);
    }


    @Test
    public void  findAllTest() throws Exception{

        Pageable pageable = PageRequest.of(0,5);
        when(pessoaService.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(cenarioTestes())));

        mock.perform(get("/pessoa")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("content[0].id", is(1)))
                            .andExpect(jsonPath("content[0].nome", is("Rocky Balboa")))
                            .andExpect(jsonPath("content[0].cpf", is("88129054809")))
                            .andExpect(jsonPath("content[0].dataNascimento", is("1970-12-25")));
    }


    @Test
    public void createTest() throws Exception {

        when(pessoaService.create(any())).thenReturn( cenarioTestes() );

        mock.perform(post("/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString( cenarioTestes() ))
                        .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isCreated())
                            .andExpect(jsonPath("$.id", is(1)))
                            .andExpect(jsonPath("$.nome", is("Rocky Balboa")))
                            .andExpect(jsonPath("$.cpf", is("88129054809")))
                            .andExpect(jsonPath("$.dataNascimento", is("1970-12-25")));
    }


    @Test
    public void updateTest() throws Exception {

        when(pessoaService.update(any(Long.class), any(Pessoa.class))).thenReturn( cenarioTestes() );

        mock.perform(put("/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString( cenarioTestes() ))
                        .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.id", is(1)))
                            .andExpect(jsonPath("$.nome", is("Rocky Balboa")))
                            .andExpect(jsonPath("$.cpf", is("88129054809")))
                            .andExpect(jsonPath("$.dataNascimento", is("1970-12-25")));
    }


    @Test
    public void deleteTest() throws Exception{
        mock.perform(delete("/pessoa/1")).andExpect(status().isNoContent());

        verify(pessoaService).delete(1L);
    }

}
