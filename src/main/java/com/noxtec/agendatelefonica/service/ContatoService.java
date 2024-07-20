package com.noxtec.agendatelefonica.service;

import com.noxtec.agendatelefonica.domain.dto.ContatoDTO;
import com.noxtec.agendatelefonica.domain.mapper.ContatoMapper;
import com.noxtec.agendatelefonica.model.Contato;
import com.noxtec.agendatelefonica.respository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ContatoMapper contatoMapper;

    public ResponseEntity<ContatoDTO> salvarContatos(ContatoDTO contatoDTO) {
        try {
            Optional<Contato> existente = contatoRepository.findByCelular(contatoDTO.getCelular());
            if (existente.isPresent()) {
                return new ResponseEntity("Contato já cadastrado com este celular.", HttpStatus.BAD_REQUEST);
            }

            Contato contato = contatoRepository.save(contatoMapper.convertToContato(contatoDTO));
            return new ResponseEntity<>(contatoMapper.convertToContatoDTO(contato), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ContatoDTO>> listarContatos() {
        try {
            List<Contato> contatos = contatoRepository.findAll();
            List<ContatoDTO> contatoDTOs = contatos.stream()
                    .map(contatoMapper::convertToContatoDTO)
                    .toList();
            return new ResponseEntity<>(contatoDTOs, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ContatoDTO> atualizarContatos(ContatoDTO contatoDTO) {
        try {
            Contato contato = contatoRepository.findById(contatoDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Contato não encontrado."));
            contato.setNome(contatoDTO.getNome());
            contato.setEmail(contatoDTO.getEmail());
            contato.setCelular(contatoDTO.getCelular());
            contato.setTelefone(contatoDTO.getTelefone());
            contato.setFavorito(contatoDTO.isFavorito());
            contato.setAtivo(contatoDTO.isAtivo());
            contato.setDataCadastro(contatoDTO.getDataCadastro());
            contatoRepository.save(contato);
            return new ResponseEntity<>(contatoMapper.convertToContatoDTO(contato), HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> inativarContatos(Long id) {
        try {
            Contato contato = contatoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Contato não encontrado."));
            contato.setAtivo(false);
            contatoRepository.save(contato);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
