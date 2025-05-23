package com.squarix.backend_project.controller;

import com.squarix.backend_project.model.Materia;
import com.squarix.backend_project.model.Usuario;
import com.squarix.backend_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "https://my-app-uybl.onrender.com/")
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping
    public List<Usuario> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getMessageById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }


    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return repo.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return repo.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/{id}/materias")
    public ResponseEntity<Set<Materia>> getMateriasEstudiantes(@PathVariable Long id) {
        return repo.findById(id)
                .map(estudiante -> ResponseEntity.ok(estudiante.getMaterias()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/materiasImpartidas")
    public ResponseEntity<Set<Materia>> getMateriasImpartidas(@PathVariable Long id) {
        return repo.findById(id)
                .map(profesor -> ResponseEntity.ok(profesor.getMateriasImpartidas()))
                .orElse(ResponseEntity.notFound().build());
    }
}