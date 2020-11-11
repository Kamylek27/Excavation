package com.kamil.excavation.repository;

import com.kamil.excavation.model.Subexcavation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubexcavationRepository extends JpaRepository<Subexcavation, Long> {
    Optional<Subexcavation> findByName(String subexcavationName);
}