package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.Model.Lawyer;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.LawyerRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LawyerService {
    private final LawyerRepository lawyerRepository;

    public List<Lawyer> getLawyers(){
        return lawyerRepository.findAll();
    }

    public void addLawyer(Lawyer lawyer){
        lawyerRepository.save(lawyer);
    }

    public boolean updateLawyer(Integer id,Lawyer lawyer){
        Lawyer oldLawyer = lawyerRepository.findLawyerById(id);

        if(oldLawyer == null){
            return false;
        }

        oldLawyer.setEmail(lawyer.getEmail());
        oldLawyer.setFullName(lawyer.getFullName());
        oldLawyer.setPhoneNumber(lawyer.getPhoneNumber());
        oldLawyer.setPassword(lawyer.getPassword());
        oldLawyer.setCity(lawyer.getCity());
        oldLawyer.setConsultationPrice(lawyer.getConsultationPrice());
        oldLawyer.setLicenseNumber(lawyer.getLicenseNumber());
        oldLawyer.setSpecialty(lawyer.getSpecialty());
        lawyerRepository.save(oldLawyer);
        return true;
    }

    public boolean deleteLawyer(Integer id){
        Lawyer oldLawyer = lawyerRepository.findLawyerById(id);

        if(oldLawyer == null){
            return false;
        }

        lawyerRepository.delete(oldLawyer);
        return true;
    }

    public List<Lawyer> getLawyersByCity(String City){
        return lawyerRepository.findLawyerByCityAndStatus(City,"active");
    }

    public List<Lawyer> getLawyersBySpecialty(String Specility){
        return lawyerRepository.findLawyerBySpecialtyAndStatus(Specility,"active");
    }

    public List<Lawyer> getLawyersByPrice(double min,double max){
        return lawyerRepository.findLawyerByConsultationPriceBetweenAndStatus(min,max,"active");
    }

    public List<Lawyer> getActiveLawyers(){
        return lawyerRepository.findLawyerByStatus("active");
    }

    public List<Lawyer> getPendingLawyers(){
        return lawyerRepository.findLawyerByStatus("pended");
    }

    public List<Lawyer> lawyerFilter(String specialty,String city,double max){
        return lawyerRepository.lawyerFilter(specialty, city, max);
    }

    public int login(String email,String Password){
        Lawyer lawyer = lawyerRepository.findLawyerByEmail(email);

        if(lawyer==null){
            return 0;
        }

        if(lawyer.getPassword().equals(Password)){
            return 1;
        }
        return 2;
    }

}
