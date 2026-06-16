package br.com.api.auth.service;

import br.com.api.auth.dto.LoginRequestDTO;
import br.com.api.auth.dto.LoginResponseDTO;
import br.com.api.auth.dto.RegisterRequestDTO;
import br.com.api.auth.dto.RegisterResponseDTO;
import br.com.api.auth.entity.User;
import br.com.api.auth.exception.EmailAlreadyExistsException;
import br.com.api.auth.exception.InvalidCredentialsException;
import br.com.api.auth.repository.RoleRepository;
import br.com.api.auth.repository.UserRepository;

import br.com.api.auth.entity.Role;
import br.com.api.auth.exception.RoleNotFoundException;
import br.com.api.auth.security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDTO(
                token,
                "Bearer",
                user.getId(),
                user.getRole().getName()
        );
    }

    @Transactional
    public RegisterResponseDTO register(RegisterRequestDTO request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException();
        }

        Role role = roleRepository
                .findByName("USER")
                .orElseThrow(RoleNotFoundException::new);

        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole(role);

        User savedUser = userRepository.save(user);

        return new RegisterResponseDTO(
                "Usuário cadastrado com sucesso",
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole().getName()
        );
    }
}