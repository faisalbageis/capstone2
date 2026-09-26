package org.example.capstone2.Repository;

import org.example.capstone2.Model.Lawyer;
import org.example.capstone2.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {
    Request findRequestById(Integer id);
    List<Request> findRequestByLawyerId(Integer lawyerId);
    List<Request> findRequestByUserId(Integer UserId);
}
