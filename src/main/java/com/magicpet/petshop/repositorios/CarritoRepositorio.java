
package com.magicpet.petshop.repositorios;

import com.magicpet.petshop.entidades.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepositorio extends JpaRepository<Carrito, String>{
    
}
