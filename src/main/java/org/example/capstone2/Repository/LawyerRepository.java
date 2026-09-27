package org.example.capstone2.Repository;

import org.example.capstone2.Model.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer,Integer> {
    Lawyer findLawyerById(Integer id);
    Lawyer findLawyerByEmail(String email);

    List<Lawyer> findLawyerByCityAndStatus(String city,String Status);
    List<Lawyer> findLawyerBySpecialtyAndStatus(String Specialty,String Status);
    List<Lawyer> findLawyerByConsultationPriceBetweenAndStatus(Double min,Double max,String status);
    List<Lawyer> findLawyerByStatus(String status);

    @Query("SELECT l FROM Lawyer l WHERE l.status = 'active' " +
            "AND l.specialty = :specialty " +
            "AND l.city = :city " +
            "AND l.consultationPrice <= :max")
    List<Lawyer> lawyerFilter(
            @Param("specialty") String specialty,
            @Param("city") String city,
            @Param("max") double max);
}
