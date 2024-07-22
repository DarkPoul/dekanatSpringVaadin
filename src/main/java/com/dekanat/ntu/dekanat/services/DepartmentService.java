package com.dekanat.ntu.dekanat.services;

import com.dekanat.ntu.dekanat.models.DepartmentModel;
import com.dekanat.ntu.dekanat.repository.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    public DepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public String getDepTitle(Long id){
        return departmentRepo.findById(id).isPresent() ? departmentRepo.findById(id).get().getTitle() : "false";
    }

    public String getDepId(String title) {
        return departmentRepo.findByTitle(title).getTitle();
    }

    public List<String> getAllDepartment(){
        return departmentRepo.findAll().stream().map(DepartmentModel::getTitle).toList();
    }


}
