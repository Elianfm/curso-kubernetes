# Curso de Kubernetes
Repositorio personal siguiendo el curso "Microservicios Guía Completa de Docker & Kubernetes" de Andrés Guzmán en Udemy.
Este repositorio sigue el contenido del curso usando mis propios comentarios y ejemplos.

## Microservicios
### ¿Qué son?
En pocas palabras, son un conjunto de componentes pequeños y autónos que colaboran entre sí para formar una aplicación más grande. Cada microservicio es responsable de una funcionalidad específica y se comunica con otros microservicios a través de APIs. Se pueden desarrollar, desplegar y escalar de forma independiente, lo que permite una mayor flexibilidad y agilidad en el desarrollo de software, por ejemplo múltiples equipos pueden trabajar en diferentes microservicios al mismo tiempo sin interferir entre ellos, con múltiples lenguajes de programación y tecnologías.

### Características
- **Función única**: Cada microservicio está diseñado para realizar una tarea específica.
- **Independientes**: Pueden ser desarrollados, desplegados y escalados de forma independiente.
- **Registro y autodescubrimiento**: Los microservicios se registran en un servicio de descubrimiento para que otros puedan encontrarlos y comunicarse con ellos.
- **Autoescalado y alta disponibilidad**: Pueden escalarse horizontalmente para manejar cargas de trabajo variables y garantizar la disponibilidad continua.
- **Resiliencia y tolerancia a fallos**: Si un microservicio falla, no afecta a toda la aplicación, lo que mejora la resiliencia general del sistema.
- **Balance de carga**: Distribuyen las solicitudes entrantes entre múltiples instancias para optimizar el rendimiento y la disponibilidad.
- **Configuraciones y entornos**: Permiten configuraciones específicas para diferentes entornos (desarrollo, pruebas, producción) y administrar las configuraciones de forma centralizada.
- **API Gateway o Puerta de enlace**: Actúa como un punto de entrada único para todas las solicitudes a los microservicios, gestionando la autenticación, autorización y enrutamiento de solicitudes.

