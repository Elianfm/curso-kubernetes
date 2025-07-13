package com.elianfm.springcloud.msvc.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// La anotación @EnableFeignClients permite que el microservicio pueda comunicarse con otros microservicios a través de Feign
// Feign es una herramienta que simplifica la creación de clientes HTTP en aplicaciones Spring Boot
// En este caso, se utilizará para comunicarse con el microservicio de usuarios
// para asignar, crear o eliminar usuarios en los cursos
@EnableFeignClients
@SpringBootApplication
public class CursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosApplication.class, args);
	}

}
