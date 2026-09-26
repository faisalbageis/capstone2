package org.example.capstone2.Repository;

import org.example.capstone2.Model.Raiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaitingRepository extends JpaRepository<Raiting,Integer> {
    Raiting findRaitingById(Integer id);
    Raiting findRaitingByLawyerId(Integer lawyerId);
}
