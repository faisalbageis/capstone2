package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.Model.Case;
import org.example.capstone2.Model.Lawyer;
import org.example.capstone2.Model.Raiting;
import org.example.capstone2.Model.Review;
import org.example.capstone2.Repository.CaseRepository;
import org.example.capstone2.Repository.LawyerRepository;
import org.example.capstone2.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CaseRepository caseRepository;
    private final RaitingService raitingService;
    private final EmailService emailService;
    private final LawyerRepository lawyerRepository;

    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    public int addReview(Review review){

        Case oldCase = caseRepository.findCaseById(review.getCaseId());

        if(oldCase == null){
            return 0;
        }
        if(!oldCase.getStatus().equalsIgnoreCase("closed")){
            return 1;
        }

        Review review1 = reviewRepository.findReviewByCaseId(review.getCaseId());
        if(review1 != null){
            return 2;
        }
        reviewRepository.save(review);
        Lawyer lawyer = lawyerRepository.findLawyerById(oldCase.getLawyerId());
        emailService.sendEmail(lawyer.getEmail(),
                "New Review Received",
                "A user has submitted a new review for your case.");

        Raiting raiting = raitingService.getLawyerRaiting(oldCase.getLawyerId());
        if(raiting==null){
            Raiting r=new Raiting();
            r.setLawyerId(oldCase.getLawyerId());
            raitingService.addRaiting(r);
        }else {
            raitingService.updateRaiting(raiting.getLawyerId());
        }
        return 3;
    }

    public int updateReview(Integer id, Review review){

        Case oldCase = caseRepository.findCaseById(review.getCaseId());

        if(oldCase == null){
            return 0;
        }

        Review oldReview = reviewRepository.findReviewById(id);

        if(oldReview == null){
            return 1;
        }

        oldReview.setRating(review.getRating());
        oldReview.setComment(review.getComment());

        reviewRepository.save(oldReview);

        Lawyer lawyer = lawyerRepository.findLawyerById(oldCase.getLawyerId());
        emailService.sendEmail(lawyer.getEmail(),
                "Review Updated",
                "A user has updated their review for your case.");
        Raiting raiting = raitingService.getLawyerRaiting(oldCase.getLawyerId());
        if(raiting==null){
            Raiting r=new Raiting();
            r.setLawyerId(oldCase.getLawyerId());
            raitingService.addRaiting(r);
        }else {
            raitingService.updateRaiting(raiting.getLawyerId());
        }
        return 2;
    }

    public boolean deleteReview(Integer id){

        Review oldReview = reviewRepository.findReviewById(id);

        if(oldReview == null){
            return false;
        }

        reviewRepository.delete(oldReview);
        return true;
    }
    public Review getCaseReview(Integer CaseID){
       return reviewRepository.findReviewByCaseId(CaseID);
    }
}