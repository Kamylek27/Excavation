package com.kamil.excavation.repository;

import com.kamil.excavation.model.Subexcavation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubexcavationRepository extends JpaRepository<Subexcavation, Long> {
    Optional<Subexcavation> findByName(String subexcavationName);
}