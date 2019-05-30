package com.zlrx.springreact.springreact.service;

import com.zlrx.springreact.springreact.domain.User;
import com.zlrx.springreact.springreact.exception.BusinessException;
import com.zlrx.springreact.springreact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public User saveUser(User user) {
        UserDetails existingUser = loadUserByUsername(user.getUsername());
        if (Objects.nonNull(existingUser)) {
            throw new BusinessException("User already exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


}
