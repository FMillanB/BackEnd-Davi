package com.squarix.backend_project.controller;

import com.squarix.backend_project.model.Materia;
import com.squarix.backend_project.model.Usuario;
import com.squarix.backend_project.repository.MateriaRepository;
import com.squarix.backend_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materias")
@CrossOrigin(origins = "https://my-app-uybl.onrender.com/")
public class MateriaController {
    @Autowired
    private MateriaRepository materiaRepo;
    @Autowired
    private UserRepository usuarioRepo;

    @GetMapping
    public List<Materia> getAll() {
        return materiaRepo.findAll();
    }

    @GetMapping("/{id}")
    public Materia getById(@PathVariable Long id) {
        return materiaRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Materia create(@RequestBody Materia materia) {
        // Si viene el profesor ya cargado, lo asocia
        if (materia.getProfesor() != null && materia.getProfesor().getId() != null) {
            Optional<Usuario> profOpt = usuarioRepo.findById(materia.getProfesor().getId());
            profOpt.ifPresent(materia::setProfesor);
        }
        return materiaRepo.save(materia);
    }

    @PutMapping("/{id}")
    public Materia update(@PathVariable Long id, @RequestBody Materia materia) {
        materia.setId(id);
        // Asociar profesor si corresponde
        if (materia.getProfesor() != null && materia.getProfesor().getId() != null) {
            usuarioRepo.findById(materia.getProfesor().getId()).ifPresent(materia::setProfesor);
        }
        return materiaRepo.save(materia);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        materiaRepo.deleteById(id);
    }

    // Asociar estudiante a materia
    @PostMapping("/{materiaId}/agregar-estudiante/{usuarioId}")
    public Materia agregarEstudiante(@PathVariable Long materiaId, @PathVariable Long usuarioId) {
        Materia materia = materiaRepo.findById(materiaId).orElseThrow();
        Usuario estudiante = usuarioRepo.findById(usuarioId).orElseThrow();
        materia.getEstudiantes().add(estudiante);
        return materiaRepo.save(materia);
    }

    // Quitar estudiante de materia
    @DeleteMapping("/{materiaId}/eliminar-estudiante/{usuarioId}")
    public Materia eliminarEstudiante(@PathVariable Long materiaId, @PathVariable Long usuarioId) {
        Materia materia = materiaRepo.findById(materiaId).orElseThrow();
        Usuario estudiante = usuarioRepo.findById(usuarioId).orElseThrow();
        materia.getEstudiantes().remove(estudiante);
        return materiaRepo.save(materia);
    }
}