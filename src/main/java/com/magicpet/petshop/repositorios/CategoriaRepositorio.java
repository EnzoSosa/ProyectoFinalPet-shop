
package com.magicpet.petshop.repositorios;

import com.magicpet.petshop.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, String>{
    
}
