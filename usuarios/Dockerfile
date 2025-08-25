# =============================================
# ¿Qué es Docker y para qué sirve?
# =============================================
# Docker es una plataforma que permite crear, ejecutar y gestionar contenedores. Un contenedor es una unidad ligera y portátil que incluye todo lo necesario para ejecutar una aplicación: código, dependencias y configuración.
# Los contenedores aseguran que la aplicación se ejecute igual en cualquier entorno, ya sea local, en servidores o en la nube.
#
# =============================================
# Algunos comandos para Docker:
# =============================================
# docker build -t usuarios . -f .\usuarios\Dockerfile
#   Construye una imagen llamada "usuarios" usando este Dockerfile.
#
# docker image ls
#   Lista todas las imágenes disponibles en tu sistema.
#
# docker run | stop | start
#   Ejecuta, detiene o inicia un contenedor.
#
# docker run -p 8001:8001 usuarios
#   Ejecuta un contenedor de la imagen "usuarios" y expone el puerto 8001.
#
# docker run -p 8001:8001 --rm usuarios
#   --rm elimina el contenedor automáticamente al detenerlo.
#
# docker run -p 8001:8001 --rm -it usuarios /bin/sh
#   -it permite la interacción con el contenedor a través de la terminal.
#   con /bin/sh se inicia un shell dentro del contenedor.
#   Es importante que el dockerfile no tenga un ENTRYPOINT definido, ya que va
#   a sobreescribir el comando por defecto, en su lugar se puede usar CMD.
#
# docker run usuarios:v1 docker run usuarios:v2
#   Ejecuta dos contenedores de diferentes versiones de la imagen "usuarios".
#
# docker run --name <nombre_contenedor> <imagen>
#   Ejecuta un contenedor, --name sirve para asignar un nombre específico al contenedor.
#
# docker run -d <imagen>
#   Ejecuta un contenedor en segundo plano (detached mode).
#
# docker start
#   Inicia un contenedor detenido, por defecto es detached mode.
#
# docker ps
#   Muestra los contenedores en ejecución.
#
# docker ps -a
#   Muestra todos los contenedores, incluyendo los detenidos.
#
# docker stop <id_contenedor>
#   Detiene un contenedor en ejecución.
#
# docker stop $(docker ps -q) || Detiene todos los contenedores en ejecución.
#
# docker rm <id_contenedor> || docker container prune
#   Elimina un contenedor detenido || elimina todos los contenedores detenidos.
#
# docker tag o docker build -t 
#   Etiqueta una imagen existente o crea una nueva imagen con un nombre específico.
# 
# docker images
#   Lista todas las imágenes disponibles en el sistema.
#
# docker rmi o docker image prune
#   Elimina una o más imágenes del sistema. (Que no están siendo utilizadas por contenedores)
#
# docker image inspect <nombre_imagen>
#   Muestra información detallada sobre una imagen específica.
#
# docker container inspect <id_contenedor>
#   Muestra información detallada sobre un contenedor específico.
#
# docker logs <id_contenedor>
#   Muestra los logs de un contenedor en ejecución.
#
# docker attach -f <id_contenedor>
#   Conecta la terminal al flujo de entrada/salida de un contenedor en ejecución.
#
# docker exec
#   Ejecuta un comando en un contenedor en ejecución.
#
# docker cp <origen> <destino>
# Desde el host hacia el contenedor es docker cp <origen> <id_contenedor o nombre>:<destino>
#   Ej: docker cp .\Login.java eloquent_cray:/app/Login.java
# Desde el contenedor hacia el host es docker cp <id_contenedor>:<origen> <destino>
#   Ej: docker cp eloquent_cray:/app/Login.java .\Login2.java
# Copiar los logs del contenedor:
#   Primero en application.properties se indica donde guardar los logs
#   logging.file.path=/app/logs
#   Luego un cp: docker cp wizardly_gates:/app/logs .\logs
#
# Recomendable usar --restart=always en producción, ya que garantiza que el contenedor se reinicie automáticamente en caso de fallos.
#
# docker image|container inspect <nombre_imagen o id_contenedor>
#   Muestra información detallada sobre una imagen o contenedor específico.
#
# Nombres y Etiquetas
#   Las imágenes y contenedores pueden tener nombres y etiquetas para identificarlos fácilmente.
#   Las etiquetas se utilizan para versionar imágenes y facilitar su gestión, 
#   mientras que los nombres permiten una identificación más sencilla.
#   Por ejemplo, openjdk:17-jdk-alpine donde <nombre> es "openjdk" y <etiqueta> es "17-jdk-alpine".
#   Para dar un nombre a una imagen o contenedor, se puede usar la opción -t o --name al crearla.
#   por ejemplo: docker run -d --name mi_contenedor usuarios
#   Para dar una etiqueta a una imagen, se puede usar la opción -t al construirla.
#   por ejemplo: docker build -t usuarios:1.0 .
#
# Para redes 
#   docker network --help || para ver los comandos relacionados con redes.
#   docker network create <nombre_red> || para crear una nueva red.
#   docker network ls || para listar las redes existentes.
#   docker run -p <puerto_host>:<puerto_contenedor> -d --rm --name <nombre_contenedor> --network <nombre_red> <imagen> || para ejecutar un contenedor en una red específica.
#
# Para bases de datos como MySQL o PostgreSQL
#   docker pull mysql:<version> 
#   docker run -d -p 3307:3306 --name <nombre_contenedor> --network <nombre_red> -e MYSQL_ROOT_PASSWORD=<password> -e MYSQL_DATABASE=<nombre_bd> mysql:<version>
#   docker run -p 5532:5432 --name <nombre_contenedor> --network <nombre_red> -e POSTGRES_USER=<usuario> -e POSTGRES_PASSWORD=<password> -e POSTGRES_DB=<nombre_bd> postgres:<version>
#   IMPORTANTE; si se rm un contenedor de BD se perderán también los datos. Para solucionar esto se recomienda usar volúmenes.
#   Los volúmenes permiten persistir los datos incluso si el contenedor se elimina. Simplemente es agregar la opción -v al ejecutar el contenedor.
#   incluyendo la ruta donde se van a persistir los datos (se almacenan en el host)
#   Ej; docker run -d -p 3307:3306 --name mysql9 --network spring -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=msvc_usuarios -v data-mysql:/var/lib/mysql --restart=always mysql:9
#   Ej; docker run -p 5532:5432 --name postgres17 --network spring -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=msvc_cursos -v data-postgres:/var/lib/postgresql/data --restart=always -d postgres:17-alpine
#   docker volume ls || sirve para listar los volúmenes existentes.
#
# Contenedores utilitarios
#   Ejecutan tareas específicas, como la creación de bases de datos, la ejecución de scripts de migración, etc.
#   docker run -it --rm --network <nombre_red> <imagen> bash
#   Ej; docker run -it --rm --network spring mysql:9 bash (o /bin/bash)
#   mysql -h mysql9 -u root -p (salir con exit)
#   Ej; docker run -it --rm --network spring postgres:14-alpine psql -h postgres17 -U postgres
#   \c msvc_cursos; \dt; \d+; exit
#
# Arguments y Environment
#   Docker soporta dos tipos de variables que podemos configurar: Arguments en tiempo de construcción y Environment en tiempo de ejecución.
#   ARG se utiliza como variables solo dentro del Dockerfile, no disponible en el ENTRYPOINT/CMD, tampoco en el código o aplicación
#   docker build + --build-arg <nombre>=<valor>
#   ej; docker build -t usuarios --build-arg PORT_APP=8080 . -f .\usuarios\Dockerfile
#   ENV se utiliza dentro del Dockerfile y disponible también en el código fuente
#   docker run + -e o --env <nombre>=<valor>
#   ej; docker run -p 8001:8090 --env PORT=8090 -d --rm --name usuarios --network spring usuarios
#   o usar un env-file con docker run -p 8001:8091 --env-file .\usuarios\env -d --rm --name usuarios --network spring usuarios
#
#
# Hay muchos comandos más, se pueden consultar con:
# docker --help
# docker ps --help
# docker images --help
# docker image --help
# docker stop --help
# docker start --help
# docker rm --help
# docker rmi --help
# docker container --help
# docker run --help
#   Muestra la ayuda de Docker y sus comandos disponibles.
#
# =============================================
# ¿Cómo funcionan las capas en Docker?
# =============================================
# Cada instrucción (FROM, COPY, RUN, etc.) crea una capa en la imagen. Si una capa no cambia, Docker la reutiliza en futuras construcciones.
# Por ejemplo, si solo cambias el código fuente, Docker reutiliza las capas de dependencias y base, acelerando el build.
# Es buena práctica copiar primero los archivos de dependencias (pom.xml) y luego el código fuente, para aprovechar mejor el cache de Docker.
# =============================================
# ¿Qué son los multi-stage builds en Docker?
# =============================================
# Los multi-stage builds permiten crear imágenes más pequeñas y seguras, usando varias etapas en el mismo Dockerfile.
# En cada etapa puedes usar una imagen base diferente y copiar solo los archivos necesarios al resultado final.
# Lo que se modifica respecto a un Dockerfile tradicional es que se agregan varias instrucciones FROM y se usa COPY --from=<etapa> para transferir solo el artefacto final (por ejemplo, el JAR) a la imagen definitiva.
# Así, la imagen final solo contiene el ejecutable y el entorno de ejecución, no el código fuente ni las herramientas de construcción.


ARG MSVC_NAME=usuarios

# Imagen base oficial de OpenJDK 17.0.2. Esta capa incluye todo el entorno de Java necesario para ejecutar aplicaciones.
# FROM openjdk:17.0.2

# Esta imagen base incluye el JDK de OpenJDK 17 en una versión ligera (Alpine).
FROM openjdk:17-jdk-alpine AS builder

# No es necesario volver a asignar el valor a la variable, solamente declararla
ARG MSVC_NAME

# Establece el directorio de trabajo dentro del contenedor. Todas las instrucciones siguientes se ejecutarán desde aquí.
WORKDIR /app/$MSVC_NAME
RUN mkdir ./logs
# Copia el archivo pom.xml principal al directorio /app. Esto permite instalar dependencias antes de copiar el código fuente completo.
COPY ./pom.xml /app
# Copia la carpeta .mvn (wrapper de Maven) y los archivos necesarios para construir el proyecto.
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

# Este comando estaba en el curso pero se comenta porque no funcionaba correctamente en el entorno local.
# RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspringboot.repackage.skip && rm -r ./target/
# En teoría, este comando compilaría el proyecto y eliminaría la carpeta target, pero puede fallar si no están todas las dependencias o el código fuente copiado.

# Descarga todas las dependencias necesarias del proyecto sin compilar el código. Esto aprovecha las capas de Docker:
# Si el pom.xml no cambia, esta capa se reutiliza y no se descargan las dependencias de nuevo, acelerando la construcción.
RUN ./mvnw dependency:go-offline

# Copia el código fuente de la aplicación al contenedor. Si hay cambios en el código, Docker reconstruye las capas siguientes.
COPY ./$MSVC_NAME/src ./src

# Compila el proyecto y genera el archivo JAR, omitiendo los tests para acelerar el proceso.
RUN ./mvnw clean package -DskipTests


###############################################################
# Segunda etapa: solo para ejecutar la aplicación
# Usamos una imagen ligera de Java y copiamos únicamente el JAR generado
# desde la etapa "builder". Así, la imagen final no incluye el código fuente
# ni herramientas de construcción, solo el ejecutable y el entorno necesario.
FROM openjdk:17-jdk-alpine
WORKDIR /app 

# Volvermos a declarar las variable de entorno MSVC_NAME porque estamos en otra etapa. 
# Se deben volver a definir después de cada FROM y antes del FROM de la siguiente etapa
ARG MSVC_NAME
ARG TARGET_FOLDER=/app/$MSVC_NAME/target/
COPY --from=builder $TARGET_FOLDER/$MSVC_NAME-0.0.1-SNAPSHOT.jar .


ARG PORT_APP=8001
# Con ENV se pueden definir variables de entorno, debe tener el mismo nombre que en el application.properties
ENV PORT=$PORT_APP
# Expone el puerto 8001 para que la aplicación sea accesible desde fuera del contenedor.
EXPOSE $PORT

# Comando de entrada: ejecuta la aplicación Spring Boot usando el JAR generado.
#ENTRYPOINT ["java", "-jar", "./usuarios-0.0.1-SNAPSHOT.jar"]

# Con CMD se puede especificar un comando por defecto que se ejecutará al iniciar el contenedor.
# A diferencia de ENTRYPOINT, CMD permite que el usuario sobrescriba el comando al ejecutar el contenedor.
CMD ["java", "-jar", "./usuarios-0.0.1-SNAPSHOT.jar"]
