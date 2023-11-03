package com.courser.courses.controllers;

import com.courser.courses.dtos.AdminDTO;
import com.courser.courses.models.Admin;
import com.courser.courses.models.Role;
import com.courser.courses.services.AdminService;
import com.courser.courses.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admins")
    public Set<AdminDTO> getAdmins() {
        return adminService.getAdminsDTO();
    }

    @GetMapping("/admins/current")
    public AdminDTO getAdminCurrent(Authentication authentication) {
        return adminService.getCurrentAdmin(authentication.getName());
    }

    @PostMapping("/admins")
    public ResponseEntity<Object> registerAdmin(
            @RequestParam String fullName, @RequestParam String email, @RequestParam String password, Authentication authentication) {

        if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
            return new ResponseEntity<>("Please don't leave any empty fields.", HttpStatus.FORBIDDEN);
        }

        Admin authAdmin = adminService.findByEmail(authentication.getName());
        if (authAdmin == null) {
            return new ResponseEntity<>("You need to be an admin to register another one.", HttpStatus.FORBIDDEN);
        }
        if (adminService.existsByEmail(email)) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.FORBIDDEN);
        }

        adminService.saveAdmin(new Admin(fullName, email, passwordEncoder.encode(password), Role.ADMIN, true));
        return new ResponseEntity<>("Admin has been created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<Object> updateAdmin(
            @RequestBody Admin newAdmin,
            @PathVariable Long id, Authentication authentication) {

        Admin oldAdmin = adminService.findById(id);
        if (oldAdmin == null) {
            return new ResponseEntity<>("Couldn't find a admin to update with that id.", HttpStatus.FORBIDDEN);
        }
        Admin authAdmin = adminService.findByEmail(authentication.getName());
        if (authAdmin == null) {
            return new ResponseEntity<>("You need to be an admin to update another one.", HttpStatus.FORBIDDEN);
        }
        if (oldAdmin.getRole() != Role.ADMIN) {
            return new ResponseEntity<>("This user is not an admin.", HttpStatus.FORBIDDEN);
        }

        oldAdmin.setFullName(newAdmin.getFullName());
        oldAdmin.setEmail(newAdmin.getEmail());
        oldAdmin.setPassword(newAdmin.getPassword());
        oldAdmin.setActive(newAdmin.getActive());

        adminService.saveAdmin(oldAdmin);
        return ResponseEntity.ok("Updated admin data");
    }

    @PatchMapping("/admins/{id}")
    public ResponseEntity<Object> removeAdmin(@PathVariable Long id) {

        Admin admin = adminService.findById(id);
        if(admin == null) {
            return new ResponseEntity<>("This admin doesn't exist.", HttpStatus.FORBIDDEN);
        }
        if(!admin.getActive()){
            return new ResponseEntity<>("This admin is already removed", HttpStatus.FORBIDDEN);
        }

        admin.setActive(false);
        adminService.saveAdmin(admin);

        return new ResponseEntity<>("Admin removed successfully", HttpStatus.OK);
    }
}
