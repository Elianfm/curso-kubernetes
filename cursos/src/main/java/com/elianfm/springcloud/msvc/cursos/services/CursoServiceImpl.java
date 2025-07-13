package com.elianfm.springcloud.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elianfm.springcloud.msvc.cursos.clients.UsuarioClientRest;
import com.elianfm.springcloud.msvc.cursos.models.Usuario;
import com.elianfm.springcloud.msvc.cursos.models.entity.Curso;
import com.elianfm.springcloud.msvc.cursos.models.entity.CursoUsuario;
import com.elianfm.springcloud.msvc.cursos.repositories.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository repository;

    // Con Feign podemos inyectar el cliente de usuarios directamente como un bean
    // Esto permite que el microservicio de cursos pueda comunicarse con el
    // microservicio
    // de usuarios sin necesidad de crear un cliente HTTP manualmente
    // Es decir, podemos llamar a los métodos del cliente como si fueran métodos
    // locales
    @Autowired
    private UsuarioClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> cursoOptional = repository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();

            Usuario usuarioC = client.detalle(usuario.getId());
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioC.getId());
            cursoUsuario.setCurso(curso);
            curso.addCursoUsuario(cursoUsuario);

            repository.save(curso);
            return Optional.of(usuarioC);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> cursoOptional = repository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            Usuario usuarioCreado = client.crear(usuario);
            CursoUsuario cursoUsuario = new CursoUsuario();

            cursoUsuario.setCurso(curso);
            cursoUsuario.setUsuarioId(usuarioCreado.getId());
            curso.addCursoUsuario(cursoUsuario);

            repository.save(curso);
            return Optional.of(usuarioCreado);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> cursoOptional = repository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            Usuario usuarioC = client.detalle(usuario.getId());
            CursoUsuario cursoUsuario = new CursoUsuario();

            cursoUsuario.setCurso(curso);
            cursoUsuario.setUsuarioId(usuarioC.getId());
            curso.removeCursoUsuario(cursoUsuario);

            repository.save(curso);
            return Optional.of(usuarioC);
        }
        return Optional.empty();
    }

}
