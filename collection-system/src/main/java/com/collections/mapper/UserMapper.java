package com.collections.mapper;

import com.collections.dto.UserRequestDTO;
import com.collections.dto.UserResponseDTO;
import com.collections.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // 🔄 DTO → ENTITY
    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        return user; // ⚠️ password set separately (important)
    }

    // 🔄 ENTITY → DTO
    public UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }
}