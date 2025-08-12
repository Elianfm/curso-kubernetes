package com.elianfm.springcloud.msvc.cursos.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.elianfm.springcloud.msvc.cursos.models.Usuario;

// @FeignClient se utiliza para definir un cliente Feign que se comunicará con otro microservicio
// es decir, este cliente se encargará de realizar las peticiones HTTP al microservicio de usuarios
// Es importante que el nombre del cliente coincida con el nombre del microservicio de usuarios que
// se encuentra en el application.properties del microservicio de usuarios como "spring.application.name=msvc-usuarios"
// Aunque ahora usamos una URL fija, cuando integramos spring cloud y kubernetes se desacoplará la URL
// y se usará un registro de servicios para encontrar el microservicio de usuarios
@FeignClient(name = "msvc-usuarios", url = "http://localhost:8001/")
public interface UsuarioClientRest {

    // Nótese que el método es declarativo, es decir, no tiene implementación
    @GetMapping("/{id}")
    public Usuario detalle(@PathVariable Long id);

    @GetMapping("/usuarios-por-curso")
    public List<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario);

    @PutMapping("/{id}")
    public Usuario editar(@RequestBody Usuario usuario, @PathVariable Long id);


}
