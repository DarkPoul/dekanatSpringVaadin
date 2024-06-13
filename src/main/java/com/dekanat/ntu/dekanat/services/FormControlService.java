package com.dekanat.ntu.dekanat.services;

import com.dekanat.ntu.dekanat.repository.FormControlRepository;
import com.dekanat.ntu.dekanat.models.FormControlModel;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class FormControlService {
    private final FormControlRepository formControlRepository;

    public FormControlService(FormControlRepository formControlRepository) {
        this.formControlRepository = formControlRepository;
    }

    public List<String> getFormControlsByTypeControl(int id) {

        return formControlRepository.findAll().stream()
                .filter(formControl -> formControl.getType() == id)
                .map(FormControlModel::getTitle)
                .collect(Collectors.toList());
    }

    public int getFormControlIdByTitle(String title) {
        return formControlRepository.findByTitle(title).getId();
    }
}
