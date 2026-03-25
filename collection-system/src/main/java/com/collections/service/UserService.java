package com.collections.service;

import com.collections.dto.UserRequestDTO;
import com.collections.dto.UserResponseDTO;
import com.collections.entity.User;
import com.collections.exception.ResourceNotFoundException;
import com.collections.mapper.UserMapper;
import com.collections.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponseDTO register(UserRequestDTO dto) {

        // 🔥 DUPLICATE CHECK
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new ResourceNotFoundException("Username already exists");
        }

        // 🔄 DTO → ENTITY
        User user = userMapper.toEntity(dto);

        // 🔐 Encrypt password separately
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // 💾 SAVE
        User saved = userRepository.save(user);

        // 🔄 ENTITY → DTO
        return userMapper.toDTO(saved);
    }
}