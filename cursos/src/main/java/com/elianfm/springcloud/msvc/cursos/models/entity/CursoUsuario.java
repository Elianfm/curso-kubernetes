package com.elianfm.springcloud.msvc.cursos.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "cursos_usuarios")
@Data
@ToString(exclude = "curso")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CursoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "usuario_id", unique = true)
    private Long usuarioId;

}
