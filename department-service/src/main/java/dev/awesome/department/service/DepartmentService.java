package dev.awesome.department.service;

import dev.awesome.department.entity.Department;
import dev.awesome.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside saveDept of Dept Service");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
        log.info("inside finddeptbyid in dept service");

        return departmentRepository.findByDepartmentId(departmentId);

    }
}
