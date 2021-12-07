package com.magicpet.petshop.repositorios;

import com.magicpet.petshop.entidades.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepositorio extends JpaRepository<Mensaje, String> {

}
