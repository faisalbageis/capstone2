package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.Model.Admin;
import org.example.capstone2.Model.Lawyer;
import org.example.capstone2.Repository.AdminRepository;
import org.example.capstone2.Repository.LawyerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final LawyerRepository lawyerRepository;
    private final EmailService emailService;

    public List<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    public void addAdmin(Admin admin){
        adminRepository.save(admin);
    }

    public boolean updateAdmin(Integer id, Admin admin){
        Admin oldAdmin = adminRepository.findAdminById(id);

        if(oldAdmin == null){
            return false;
        }

        oldAdmin.setFullName(admin.getFullName());
        oldAdmin.setEmail(admin.getEmail());
        oldAdmin.setPassword(admin.getPassword());

        adminRepository.save(oldAdmin);
        return true;
    }

    public boolean deleteAdmin(Integer id){
        Admin oldAdmin = adminRepository.findAdminById(id);

        if(oldAdmin == null){
            return false;
        }

        adminRepository.delete(oldAdmin);
        return true;
    }

    public int updateLawyerStatus(Integer adminId, Integer lawyerId, String status){

        Admin admin = adminRepository.findAdminById(adminId);

        if(admin == null){
            return 0;
        }

        Lawyer lawyer = lawyerRepository.findLawyerById(lawyerId);

        if(lawyer == null){
            return 1;
        }

        if(!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Blocked")){
            return 2;
        }

        lawyer.setStatus(status);
        lawyerRepository.save(lawyer);

        if(status.equalsIgnoreCase("Active")){
            emailService.sendEmail(lawyer.getEmail(),
                    "activate account",
                    "your Account Has Been Activated");
        }else {
            emailService.sendEmail(lawyer.getEmail(),
                    "Blocked account",
                    "your Account Has Been Blocked");
        }

        return 3;
    }
}