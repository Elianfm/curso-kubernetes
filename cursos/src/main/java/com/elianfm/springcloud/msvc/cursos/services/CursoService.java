package com.elianfm.springcloud.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import com.elianfm.springcloud.msvc.cursos.models.Usuario;
import com.elianfm.springcloud.msvc.cursos.models.entity.Curso;


public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    void eliminarCursoUsuarioPorUsuarioId(Long usuarioId);

    // Métodos para manejar la relación con usuarios (microservicio usuarios)
    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    Optional<Curso> porIdConUsuarios(Long id);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);


}
