package org.example.capstone2.Repository;

import org.example.capstone2.Model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<Case,Integer> {
    Case findCaseById(Integer id);
    List<Case> findCaseByLawyerId(Integer lawyerId);
    List<Case> findCaseByUserId(Integer userId);
}
