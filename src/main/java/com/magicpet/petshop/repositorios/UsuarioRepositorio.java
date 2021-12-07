package com.magicpet.petshop.repositorios;

import com.magicpet.petshop.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
    @Query("select u from Usuario u where u.username = :username")
    Usuario findByUsername(@Param("username") String username);
    
    @Query("select u from Usuario u where u.username = :username OR u.mail = :mail")
    Usuario findByUsernameOrMail(@Param("username") String username,@Param("mail") String mail);
    
    @Query("select u from Usuario u where u.nombre LIKE :q or u.apellido LIKE :q or u.ciudad LIKE :q or u.dni LIKE :q or u.username LIKE :q or u.mail LIKE :q ")       
     List<Usuario> findAllByQ(@Param("q") String q);

}
