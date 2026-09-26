package org.example.capstone2.Repository;

import org.example.capstone2.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Review findReviewById(Integer id);
    Review findReviewByCaseId(Integer caseId);
}
