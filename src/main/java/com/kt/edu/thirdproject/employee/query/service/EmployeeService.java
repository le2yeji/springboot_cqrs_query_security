package com.kt.edu.thirdproject.employee.query.service;

import com.kt.edu.thirdproject.common.annotation.Ktedu;
import com.kt.edu.thirdproject.employee.query.domain.EmployeeEntity;
import com.kt.edu.thirdproject.common.exception.ResourceNotFoundException;
import com.kt.edu.thirdproject.employee.query.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;    // jasypt로 저장된 비밀번호가  복호화 된다.
    @Value("${spring.datasource.password}")
    private String h2Password;

    /*@Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;

    }*/
    public List<EmployeeEntity> getEmployeeList() {
        log.info("Request to get all Employees");
        log.info("h2 password : " + h2Password );
        List<EmployeeEntity> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    // 플러시를 생략해서 dirty checking등을 하지 않으므로 약간의 성능 향상
    @Transactional(readOnly = true)
    public EmployeeEntity getEmployee(Long id) {
        log.info("Request to get Employee : {}", id);
        return employeeRepository.findById(id).get();
    }

//
//    @Cacheable("employees")
//    public List<EmployeeEntity> getEmployeeList() {
//        log.info("Request to get all Employees");
//        List<EmployeeEntity> employeeList = new ArrayList<>();
//        employeeRepository.findAll().forEach(employeeList::add);
//        return employeeList;
//    }
//
//    // 플러시를 생략해서 dirty checking등을 하지 않으므로 약간의 성능 향상
//    @Cacheable("employee")
//    @Transactional(readOnly = true)
//    public EmployeeEntity getEmployee(Long id) {
//        log.info("Request to get Employee : {}", id);
//        return employeeRepository.findById(id).get();
//    }
}
