package com.kamil.excavation.controller;

import com.kamil.excavation.dto.SubexcavationDto;
import com.kamil.excavation.model.Subexcavation;
import com.kamil.excavation.service.SubexcavationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subexcavation")
@AllArgsConstructor
public class SubexcavationController {

    private final SubexcavationService subexcavationService;

    @GetMapping
    public List<SubexcavationDto> getAllSubreddits() {
        return subexcavationService.getAll();
    }

    @GetMapping("/{id}")
    public SubexcavationDto getSubreddit(@PathVariable Long id) {
        return subexcavationService.getSubexcavation(id);
    }

    @PostMapping
    public SubexcavationDto create(@RequestBody @Valid SubexcavationDto subredditDto) {
        return subexcavationService.save(subredditDto);
    }
}
