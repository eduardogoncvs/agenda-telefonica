package com.noxtec.agendatelefonica.respository;

import com.noxtec.agendatelefonica.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Optional<Contato> findByCelular(String celular);

}
