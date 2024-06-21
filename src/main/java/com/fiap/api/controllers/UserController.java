package com.fiap.api.controllers;

import com.fiap.api.domain.User;
import com.fiap.api.dtos.DataCreatUser;
import com.fiap.api.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Void> createUser(@RequestBody DataCreatUser data, UriComponentsBuilder componentsBuilder) throws Exception {
        User user = userService.createUser(data);
        URI uri  = componentsBuilder.path("/user/create/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
