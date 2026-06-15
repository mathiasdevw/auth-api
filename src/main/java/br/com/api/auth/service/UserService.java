package br.com.api.auth.service;

import br.com.api.auth.repository.RoleRepository;
import br.com.api.auth.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


}
