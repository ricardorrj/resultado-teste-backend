package com.ricardorrj.cadastroProcesso.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardorrj.cadastroProcesso.domain.Pessoa;
import com.ricardorrj.cadastroProcesso.domain.Processo;
import com.ricardorrj.cadastroProcesso.dto.ProcessoDTO;
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
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProcessoResourceTest {

    @MockBean
    private ProcessoService processoService;

    @MockBean
    private PessoaService pessoaService;

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper objectMapper;



    private Processo cenarioTestes(){
        Pessoa pessoa = new Pessoa(1L, "Rocky Balboa", "88129054809", LocalDate.parse("1970-12-25"));
        Processo processo = new Processo(1L, 55321531L, 2021, pessoa);

        return processo;
    }


    @Test
    public void findByIdTest() throws Exception{

        when(this.processoService.findById(1L))
                .thenReturn( cenarioTestes() );

        mock.perform(get("/processo/1")
                        .content(objectMapper.writeValueAsString(Long.toString(1L)))
                        .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.id", is(1)))
                            .andExpect(jsonPath("$.numero", is(55321531)))
                            .andExpect(jsonPath("$.ano", is(2021)));

        verify(processoService).findById(1L);
    }


    @Test
    public void  findAllTest() throws Exception{

        Pageable pageable = PageRequest.of(0, 5);
        when(processoService.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(cenarioTestes())));

        mock.perform(get("/processo")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("content[0].id", is(1)))
                            .andExpect(jsonPath("content[0].numero", is(55321531)))
                            .andExpect(jsonPath("content[0].ano", is(2021)));
    }


    @Test
    public void createTest() throws Exception {

        when(this.processoService.create(any(Long.class), any(Processo.class)))
                .thenReturn( cenarioTestes() );

        mock.perform(post("/processo").param("pessoa", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString( new ProcessoDTO(cenarioTestes()) ))
                        .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isCreated())
                            .andExpect(jsonPath("$.id", is(1)))
                            .andExpect(jsonPath("$.numero", is(55321531)))
                            .andExpect(jsonPath("$.ano", is(2021)));
    }


    @Test
    public void deleteTest() throws Exception{
        mock.perform(delete("/processo/1")).andExpect(status().isNoContent());

        verify(processoService).delete(1L);
    }


}
