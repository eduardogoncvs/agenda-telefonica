package com.noxtec.agendatelefonica.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContatoDTO {

    private Long id;
    private String nome;
    private String email;
    private String celular;
    private String telefone;
    private boolean favorito;
    private boolean ativo;
    private LocalDate dataCadastro;
}
