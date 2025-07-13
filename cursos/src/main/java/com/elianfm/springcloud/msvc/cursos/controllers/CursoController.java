package com.elianfm.springcloud.msvc.cursos.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elianfm.springcloud.msvc.cursos.models.Usuario;
import com.elianfm.springcloud.msvc.cursos.models.entity.Curso;
import com.elianfm.springcloud.msvc.cursos.services.CursoService;

import feign.FeignException;
import jakarta.validation.Valid;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.porId(id);

        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get());
        } 

        return ResponseEntity.notFound().build();
        
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Curso curso, BindingResult result) {
        if(result.hasErrors()) return validar(result);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(cursoService.guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @PathVariable Long id, BindingResult result, @RequestBody Curso curso) {
        if (result.hasErrors()) return validar(result);

        Optional<Curso> cursoOptional = cursoService.porId(id);

        if (cursoOptional.isPresent()) {
            Curso cursoActualizado = cursoOptional.get();
            cursoActualizado.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(cursoService.guardar(cursoActualizado));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.porId(id);

        if (curso.isPresent()) {
            cursoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) {
        Optional<Usuario> usuarioAsignado = null;
        
        try {
            usuarioAsignado = cursoService.asignarUsuario(usuario, cursoId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Collections.singletonMap("mensaje", "No existe un usuario con ese ID" + e.getMessage()));
        }
        if (usuarioAsignado.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(usuarioAsignado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear-usuario/{cursoId}")
    public ResponseEntity<?> crearUusuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) {
        Optional<Usuario> usuarioCreado = null;
        
        try {
            usuarioCreado = cursoService.crearUsuario(usuario, cursoId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Collections.singletonMap("mensaje", "No se pudo crear el usuario" + e.getMessage()));
        }
        if (usuarioCreado.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(usuarioCreado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) {
        Optional<Usuario> usuarioEliminado = null;
        
        try {
            usuarioEliminado = cursoService.eliminarUsuario(usuario, cursoId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Collections.singletonMap("mensaje", "No se pudo eliminar el usuario" + e.getMessage()));
        }
        if (usuarioEliminado.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(usuarioEliminado.get());
        }
        return ResponseEntity.notFound().build();
    }


    // Métodos auxiliares

    private ResponseEntity<Map<String,String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    
}
