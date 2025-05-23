package com.squarix.backend_project.repository;

import com.squarix.backend_project.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {}