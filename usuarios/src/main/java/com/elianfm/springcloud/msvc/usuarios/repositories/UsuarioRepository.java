package com.elianfm.springcloud.msvc.usuarios.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.elianfm.springcloud.msvc.usuarios.models.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);    

    //Alternativa 
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Optional<Usuario> buscarPorEmail(String email);

    // Otra alternativa 
    boolean existsByEmail(String email);
}
