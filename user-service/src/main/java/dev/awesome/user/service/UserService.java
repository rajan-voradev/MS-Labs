package dev.awesome.user.service;

import dev.awesome.user.VO.Department;
import dev.awesome.user.VO.ResponseTemplateVO;
import dev.awesome.user.entity.User;
import dev.awesome.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    public User saveUser(User user) {
        log.info("inside save user in user service");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("inside getUserWithDepartment in user service");

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

//        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(), Department.class);
        String url = "http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId();
        Department department = circuitBreaker.run(() -> restTemplate.getForObject(url, Department.class),
                throwable -> new Department());
        log.info(department.getDepartmentCode());
        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }
} 
