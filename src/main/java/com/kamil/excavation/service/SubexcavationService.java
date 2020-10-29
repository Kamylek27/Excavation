package com.kamil.excavation.service;

import com.kamil.excavation.dto.SubexcavationDto;
import com.kamil.excavation.exception.SubexcavationNotFoundException;
import com.kamil.excavation.mapper.SubexcavationMapper;
import com.kamil.excavation.model.Subexcavation;
import com.kamil.excavation.repository.SubexcavationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class SubexcavationService {

    private final SubexcavationRepository subexcavationRepository;
    private final SubexcavationMapper subexcavationMapper;

    @Transactional(readOnly = true)
    public List<SubexcavationDto> getAll() {
        return subexcavationRepository.findAll()
                .stream()
                .map(subexcavationMapper::mapSubexcavationToDto)
                .collect(toList());

    }

    @Transactional
    public SubexcavationDto save(SubexcavationDto subexcavationDto){
        Subexcavation subexcavation = subexcavationRepository.save(subexcavationMapper.mapDtoToSubreddit(subexcavationDto));
        subexcavationDto.setId(subexcavation.getId());
        return  subexcavationDto;
    }


    public SubexcavationDto getSubexcavation(Long id){
        Subexcavation subexcavation = subexcavationRepository.findById(id)
                .orElseThrow(()-> new SubexcavationNotFoundException("Subexcavation not found with id -" + id));
        return subexcavationMapper.mapSubexcavationToDto(subexcavation);
    }


}
