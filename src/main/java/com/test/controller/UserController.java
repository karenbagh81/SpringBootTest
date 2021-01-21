package com.test.controller;

import com.test.model.User;
import com.test.service.interfaces.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
//@PreAuthorize("hasAuthority('MANAGER')")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/user-create")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") int id) throws NotFoundException {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user-delete/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") int id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user-update")
    public ResponseEntity updateUser(@Valid @RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok().build();
    }

}
