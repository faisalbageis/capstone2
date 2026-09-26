package org.example.capstone2.Repository;

import org.example.capstone2.Model.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer,Integer> {
    Lawyer findLawyerById(Integer id);
    Lawyer findLawyerByEmail(String email);

    List<Lawyer> findLawyerByCity(String city);
    List<Lawyer> findLawyerBySpecialty(String Specialty);
    List<Lawyer> findLawyerByConsultationPriceBetween(Double min,Double max);
}
