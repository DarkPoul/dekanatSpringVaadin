package com.dekanat.ntu.dekanat.services;

import com.dekanat.ntu.dekanat.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<String> getAllDepartments() {

        List<String> allDepartments = new ArrayList<>();
        departmentRepository.findAll().forEach(department -> {
            allDepartments.add("("+department.getId()+")" + " " + department.getTitle());
        });

        return allDepartments;
    }
}

