package com.example.aviacase.service;

import com.example.aviacase.model.User;
import com.example.aviacase.repository.UserRepository;
import com.example.aviacase.service.exception.UserLoginAlreadyExistsException;
import com.example.aviacase.service.exception.UserNotFoundException;
import com.example.aviacase.service.exception.WrongLoginOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUser(Long id) {
        final Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public User findUserByLogin(String login) {
        final User user = userRepository.findByLogin(login);
        return user;
    }

    @Transactional
    public void registration(String login, String password, String name, String surname) {
        User user = new User();
        if (login != null && !login.isBlank()) {
            if (findUserByLogin(login) == null) {
                user.setLogin(login);
            } else {
                throw new UserLoginAlreadyExistsException(login);
            }
        }
        if (name != null && !name.isBlank())
            user.setName(name);
        if (surname != null && !surname.isBlank())
            user.setSurname(surname);
        if (password != null && !password.isBlank())
            user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }

    @Transactional
    public boolean authorization(User user){
        User userDB = findUserByLogin(user.getLogin());
        if(userDB != null && encoder.matches(user.getPassword(), userDB.getPassword())){
            return true;
        }
        else{
            throw new WrongLoginOrPasswordException();
        }
    }

    @Transactional
    public User updateUser(User user, String login, String password, String name, String surname){
        User userDB = findUser(user.getId());
        if (login != null && !login.isBlank()) {
            if (findUserByLogin(login) == null) {
                userDB.setLogin(login);
            } else {
                throw new UserLoginAlreadyExistsException(login);
            }
        }
        if (name != null && !name.isBlank())
            userDB.setName(name);
        if (surname != null && !surname.isBlank())
            userDB.setSurname(surname);
        if (password != null && !password.isBlank())
            userDB.setPassword(encoder.encode(password));
        return userRepository.save(userDB);
    }

    @Transactional
    public User deleteUser(Long id) {
        final User user = findUser(id);
        userRepository.delete(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        var roles = new ArrayList<>();
        roles.add("ROLE_USER");
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList())
        );
    }
}
