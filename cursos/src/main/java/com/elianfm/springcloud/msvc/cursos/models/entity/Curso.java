package com.elianfm.springcloud.msvc.cursos.models.entity;

import java.util.ArrayList;
import java.util.List;

import com.elianfm.springcloud.msvc.cursos.models.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Entity
@Table (name = "cursos")
@Data
@AllArgsConstructor
@Builder
@ToString(exclude = {"cursoUsuarios", "usuarios"})
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name= "curso_id", nullable = true)
    private List<CursoUsuario> cursoUsuarios;

    // Usado para la relacion con el microservicio de usuarios
    // Se usa @Transient para evitar que este campo se persista en la base de datos
    @Transient 
    private List<Usuario> usuarios;

    public Curso() {
        cursoUsuarios = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void addCursoUsuario(CursoUsuario cursoUsuario) {
        this.cursoUsuarios.add(cursoUsuario);
    }

    public void removeCursoUsuario(CursoUsuario cursoUsuario) {
        this.cursoUsuarios.remove(cursoUsuario);
    }
}
