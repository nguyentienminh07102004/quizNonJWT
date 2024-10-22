package com.quiz.Service.Role;

import com.quiz.Model.Entity.RoleEntity;
import com.quiz.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public Map<String, String> getAllRoles() {
        List<RoleEntity> roles = roleRepository.findAll();
        Map<String, String> mapRoles = new TreeMap<>();
        roles.forEach(role -> mapRoles.put(role.getCode(), role.getName()));
        return mapRoles;
    }
}
