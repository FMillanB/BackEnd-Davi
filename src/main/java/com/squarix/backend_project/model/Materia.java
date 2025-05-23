package com.squarix.backend_project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JsonIgnoreProperties({"materias", "materiasImpartidas", "age", "rol", "email", "password"})
    @JoinColumn(name = "profesor_id")
    private Usuario profesor;

    @ManyToMany
    @JsonIgnoreProperties({"materias", "materiasImpartidas", "age", "rol", "email", "password"})
    @JoinTable(
            name = "materia_estudiantes",
            joinColumns = @JoinColumn(name = "materia_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> estudiantes = new HashSet<>();

    public Materia() {}
    public Materia(String nombre, Usuario profesor) {
        this.nombre = nombre;
        this.profesor = profesor;
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Usuario getProfesor() { return profesor; }
    public void setProfesor(Usuario profesor) { this.profesor = profesor; }
    public Set<Usuario> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(Set<Usuario> estudiantes) { this.estudiantes = estudiantes; }
}
