package com.squarix.backend_project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long age;
    private String rol;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "estudiantes")
    private Set<Materia> materias = new HashSet<>();

    @OneToMany(mappedBy = "profesor")
    private Set<Materia> materiasImpartidas = new HashSet<>();

    public Usuario() {}

    public Usuario( String name, Long age, String rol, String mail, String password) {
        this.name = name;
        this.age = age;
        this.rol = rol;
        this.email = mail;
        this.password = password;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getAge() { return age; }
    public void setAge(Long age) { this.age = age; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<Materia> getMaterias() { return materias; }
    public void setMaterias(Set<Materia> materias) { this.materias = materias; }

    public Set<Materia> getMateriasImpartidas() { return materiasImpartidas; }
    public void setMateriasImpartidas(Set<Materia> materiasImpartidas) { this.materiasImpartidas = materiasImpartidas; }
}