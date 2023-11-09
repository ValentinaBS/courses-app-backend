package com.courser.courses.services;

import com.courser.courses.dtos.AdminDTO;
import com.courser.courses.models.subclass.Admin;

import java.util.Set;

public interface AdminService {
    Admin findByEmail(String email);
    Admin findById(Long id);
    boolean existsByEmail(String email);

    AdminDTO getAdminDTO(Long id);
    Set<AdminDTO> getAdminsDTO();
    AdminDTO getCurrentAdmin(String email);
    void saveAdmin(Admin admin);
}
