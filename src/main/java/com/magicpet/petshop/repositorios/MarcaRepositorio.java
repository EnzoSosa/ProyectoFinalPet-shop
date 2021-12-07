
package com.magicpet.petshop.repositorios;

import com.magicpet.petshop.entidades.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepositorio extends JpaRepository<Marca, String> {
    
}
