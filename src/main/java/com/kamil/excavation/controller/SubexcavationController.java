package com.kamil.excavation.controller;

import com.kamil.excavation.dto.SubexcavationDto;
import com.kamil.excavation.service.SubexcavationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subexcavation")
@AllArgsConstructor
public class SubexcavationController {

    private final SubexcavationService subexcavationService;

    @PostMapping
    public ResponseEntity<SubexcavationDto> create(@RequestBody SubexcavationDto subexcavationDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subexcavationService.save(subexcavationDto));
    }

    @GetMapping
    public ResponseEntity<List<SubexcavationDto>> getAllSubexcavations() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subexcavationService.getAll());
    }

    @GetMapping("/view-subexcavation/{id}")
    public ResponseEntity<SubexcavationDto> getSubexcavation(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subexcavationService.getSubexcavation(id));
    }
}
