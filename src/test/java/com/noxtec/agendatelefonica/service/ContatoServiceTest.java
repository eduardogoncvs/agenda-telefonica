package com.noxtec.agendatelefonica.service;

import com.noxtec.agendatelefonica.domain.dto.ContatoDTO;
import com.noxtec.agendatelefonica.model.Contato;
import com.noxtec.agendatelefonica.respository.ContatoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ContatoServiceTest {

    @Autowired
    private ContatoService contatoService;

    @MockBean
    private ContatoRepository contatoRepository;

    @Test
    public void testSalvarContatos() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setCelular("12345678901");

        Mockito.when(contatoRepository.findByCelular(contatoDTO.getCelular())).thenReturn(Optional.empty());
        Mockito.when(contatoRepository.save(Mockito.any(Contato.class))).thenReturn(new Contato());

        ResponseEntity<ContatoDTO> response = contatoService.salvarContatos(contatoDTO);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Mockito.when(contatoRepository.findByCelular(contatoDTO.getCelular())).thenReturn(Optional.of(new Contato()));
        response = contatoService.salvarContatos(contatoDTO);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testListarContatos() {
        List<Contato> contatos = new ArrayList<>();
        contatos.add(new Contato());

        Mockito.when(contatoRepository.findAll()).thenReturn(contatos);

        ResponseEntity<List<ContatoDTO>> response = contatoService.listarContatos();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testAtualizarContatos() {
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setId(1L);
        contatoDTO.setNome("Nome Atualizado");

        Contato contato = new Contato();
        contato.setId(1L);

        Mockito.when(contatoRepository.findById(contatoDTO.getId())).thenReturn(Optional.of(contato));
        Mockito.when(contatoRepository.save(Mockito.any(Contato.class))).thenReturn(contato);

        ResponseEntity<ContatoDTO> response = contatoService.atualizarContatos(contatoDTO);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Mockito.when(contatoRepository.findById(contatoDTO.getId())).thenReturn(Optional.empty());
        response = contatoService.atualizarContatos(contatoDTO);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testInativarContatos() {
        Contato contato = new Contato();
        contato.setId(1L);

        Mockito.when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

        ResponseEntity<Void> response = contatoService.inativarContatos(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Mockito.when(contatoRepository.findById(1L)).thenReturn(Optional.empty());
        response = contatoService.inativarContatos(1L);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
