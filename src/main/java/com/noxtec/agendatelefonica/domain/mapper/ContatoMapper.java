package com.noxtec.agendatelefonica.domain.mapper;

import com.noxtec.agendatelefonica.domain.dto.ContatoDTO;
import com.noxtec.agendatelefonica.model.Contato;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {

    public Contato convertToContato(ContatoDTO contatoDTO) {
        return Contato.builder()
                .id(contatoDTO.getId())
                .ativo(contatoDTO.isAtivo())
                .email(contatoDTO.getEmail())
                .celular(contatoDTO.getCelular())
                .dataCadastro(contatoDTO.getDataCadastro())
                .favorito(contatoDTO.isFavorito())
                .nome(contatoDTO.getNome())
                .telefone(contatoDTO.getTelefone())
                .build();
    }

    public ContatoDTO convertToContatoDTO(Contato contato) {
        return ContatoDTO.builder()
                .ativo(contato.isAtivo())
                .celular(contato.getCelular())
                .dataCadastro(contato.getDataCadastro())
                .email(contato.getEmail())
                .favorito(contato.isFavorito())
                .id(contato.getId())
                .nome(contato.getNome())
                .telefone(contato.getTelefone())
                .build();
    }
}
