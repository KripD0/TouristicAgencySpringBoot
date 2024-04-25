package com.example.touristicagency.service;

import com.example.touristicagency.exceptions.UserNotFoundException;
import com.example.touristicagency.model.User;
import com.example.touristicagency.model.security.SecurityUser;
import com.example.touristicagency.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);

        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь с именем " + username + " не найден.");
        }
        log.info("Пользователь {} успешно аутентифицировался", user.get().getUserName());
        return new SecurityUser(user.get());
    }
}