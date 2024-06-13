package com.dekanat.ntu.dekanat.services;


import com.dekanat.ntu.dekanat.models.DisciplineModel;
import com.dekanat.ntu.dekanat.repository.DisciplinesRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DisciplinesService {
    private final DisciplinesRepository disciplinesRepository;

    public DisciplinesService(DisciplinesRepository disciplinesRepository) {
        this.disciplinesRepository = disciplinesRepository;
    }

    public List<String> getAllDisciplines() {

        return disciplinesRepository.findAll()
                .stream()
                .map(DisciplineModel::getTitle)
                .collect(Collectors.toList());
    }

    public String getDisciplineById(int id) {
        return disciplinesRepository.findById(id).getTitle();
    }

    public int getDisciplineIdByTitle(String title) {
        return disciplinesRepository.findByTitle(title).getId();
    }

}

