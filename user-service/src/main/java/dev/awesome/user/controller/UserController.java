package dev.awesome.user.controller;

import dev.awesome.user.VO.ResponseTemplateVO;
import dev.awesome.user.entity.User;
import dev.awesome.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("inside saveuser in user controller");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("inside getUserWithDepartment in usercontroller");
        return userService.getUserWithDepartment(userId);
    }
} 
