package com.noxtec.agendatelefonica.controller;

import com.noxtec.agendatelefonica.domain.dto.ContatoDTO;
import com.noxtec.agendatelefonica.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<ContatoDTO> salvarContatos(@RequestBody ContatoDTO contatoDTO) {
        return contatoService.salvarContatos(contatoDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<ContatoDTO>> listarContatos() {
        return contatoService.listarContatos();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    public ResponseEntity<ContatoDTO> atualizarContatos(@RequestBody ContatoDTO contatoDTO) {
        return contatoService.atualizarContatos(contatoDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/{id}")
    public ResponseEntity<?> inativarContatos(@PathVariable Long id) {
        return contatoService.inativarContatos(id);
    }
}
