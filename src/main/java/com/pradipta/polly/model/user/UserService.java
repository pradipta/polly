package com.pradipta.polly.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
