package com.fiap.api.services;

import com.fiap.api.domain.User;
import com.fiap.api.dtos.DataCreatUser;
import com.fiap.api.infra.exceptions.DuplicatedLoginException;
import com.fiap.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(DataCreatUser data) throws DuplicatedLoginException {
        User user = new User(data);
        if(!userRepository.findByLogin(user.getLogin()).isEmpty()){
            throw new DuplicatedLoginException(data.login());
        }
        user.setPassword(passwordEncoder.encode(data.password()));
        return userRepository.save(user);
    }

    

}
