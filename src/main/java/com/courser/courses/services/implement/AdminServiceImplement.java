package com.courser.courses.services.implement;

import com.courser.courses.dtos.AdminDTO;
import com.courser.courses.models.Admin;
import com.courser.courses.repositories.AdminRepository;
import com.courser.courses.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import static java.util.stream.Collectors.toSet;

@Service
public class AdminServiceImplement implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }

    @Override
    public AdminDTO getAdminDTO(Long id) {
        return new AdminDTO(this.findById(id));
    }

    @Override
    public Set<AdminDTO> getAdminsDTO() {
        return adminRepository
                .findAll()
                .stream()
                .map(AdminDTO::new)
                .collect(toSet());
    }

    @Override
    public AdminDTO getCurrentAdmin(String email) {
        return new AdminDTO(this.findByEmail(email));
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
