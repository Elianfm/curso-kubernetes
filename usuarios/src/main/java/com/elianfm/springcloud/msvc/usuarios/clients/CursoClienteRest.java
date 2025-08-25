package com.elianfm.springcloud.msvc.usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "cursos", url = "http://host.docker.internal:8002")
// Cuando el Client es también un microservicio, se puede hacer así;
@FeignClient(name = "cursos", url="${msvc.cursos.url}")
public interface CursoClienteRest {

    @DeleteMapping("/eliminar-curso-usuario/{id}")
    void eliminarCursoUsuarioPorId(@PathVariable Long id);
}
