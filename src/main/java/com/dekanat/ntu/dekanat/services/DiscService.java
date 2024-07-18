package com.dekanat.ntu.dekanat.services;

import com.dekanat.ntu.dekanat.models.DiscModel;
import com.dekanat.ntu.dekanat.repository.DiscRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscService {

    private final DiscRepo discRepo;

    public DiscService(DiscRepo discRepo) {
        this.discRepo = discRepo;
    }

    public String getDiscTitle(Long id){
        return discRepo.findById(id).isPresent() ? discRepo.findById(id).get().getTitle() : "false";
    }

    public Long getDiscId(String title){
        return discRepo.findByTitle(title).getId();
    }

    public List<String> getAllDisc(){
        return new ArrayList<>(discRepo.findAll().stream().map(DiscModel::getTitle).toList());
    }
}
