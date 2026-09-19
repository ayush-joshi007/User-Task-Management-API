package com.examples.usertaskmanagementapi.service;

import com.examples.usertaskmanagementapi.entity.Permission;
import com.examples.usertaskmanagementapi.entity.Role;
import com.examples.usertaskmanagementapi.repository.PermissionRepository;
import com.examples.usertaskmanagementapi.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityDataSeeder {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public SecurityDataSeeder(
            PermissionRepository permissionRepository,
            RoleRepository roleRepository
    ) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void seed() {

        // =========================
        // 1. Create/find permissions
        // =========================

        Permission taskRead = permissionRepository.findByName("TASK_READ")
                .orElseGet(() -> {
                    Permission permission = new Permission();
                    permission.setName("TASK_READ");
                    return permissionRepository.save(permission);
                });

        Permission taskWrite = permissionRepository.findByName("TASK_WRITE")
                .orElseGet(() -> {
                    Permission permission = new Permission();
                    permission.setName("TASK_WRITE");
                    return permissionRepository.save(permission);
                });

        Permission taskDelete = permissionRepository.findByName("TASK_DELETE")
                .orElseGet(() -> {
                    Permission permission = new Permission();
                    permission.setName("TASK_DELETE");
                    return permissionRepository.save(permission);
                });

        Permission adminAccess = permissionRepository.findByName("ADMIN_ACCESS")
                .orElseGet(() -> {
                    Permission permission = new Permission();
                    permission.setName("ADMIN_ACCESS");
                    return permissionRepository.save(permission);
                });

        // =========================
        // 2. Create/find roles
        // =========================

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_USER");
                    return roleRepository.save(role);
                });

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_ADMIN");
                    return roleRepository.save(role);
                });

        // =========================
        // 3. Assign permissions
        // =========================

        // ROLE_USER
        userRole.getPermissions().add(taskRead);
        userRole.getPermissions().add(taskWrite);

        // ROLE_ADMIN
        adminRole.getPermissions().add(taskRead);
        adminRole.getPermissions().add(taskWrite);
        adminRole.getPermissions().add(taskDelete);
        adminRole.getPermissions().add(adminAccess);

        // =========================
        // 4. Save roles
        // =========================

        roleRepository.save(userRole);
        roleRepository.save(adminRole);
    }
}