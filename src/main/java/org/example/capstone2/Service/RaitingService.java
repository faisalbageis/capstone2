package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.Model.Case;
import org.example.capstone2.Model.Lawyer;
import org.example.capstone2.Model.Raiting;
import org.example.capstone2.Model.Review;
import org.example.capstone2.Repository.CaseRepository;
import org.example.capstone2.Repository.LawyerRepository;
import org.example.capstone2.Repository.RaitingRepository;
import org.example.capstone2.Repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RaitingService {

    private final RaitingRepository raitingRepository;
    private final LawyerRepository lawyerRepository;
    private final ReviewRepository reviewRepository;
    private final CaseRepository caseRepository;
    public List<Raiting> getRaitings(){
        return raitingRepository.findAll();
    }

    public int addRaiting(Raiting raiting){

        Lawyer lawyer = lawyerRepository.findLawyerById(raiting.getLawyerId());

        if(lawyer == null){
            return 0;
        }
        double sum=0.0;
        int count=0;
        List<Case> cases = caseRepository.findCaseByLawyerId(raiting.getLawyerId());
        for(Case i:cases){
            Review review = reviewRepository.findReviewByCaseId(i.getId());
            if(review!=null){
                sum +=review.getRating();
                count++;
            }
        }
        if(count==0){
            return 1;
        }
        double raite = sum/count;
        raiting.setRating(raite);
        raitingRepository.save(raiting);
        return 2;
    }

    public boolean deleteRaiting(Integer id){

        Raiting oldRaiting = raitingRepository.findRaitingById(id);

        if(oldRaiting == null){
            return false;
        }

        raitingRepository.delete(oldRaiting);
        return true;
    }

    public Raiting getLawyerRaiting(Integer lawyerId){
        return raitingRepository.findRaitingByLawyerId(lawyerId);
    }

    public int updateRaiting(Integer lawyerId){
        Raiting oldRaiting = raitingRepository.findRaitingByLawyerId(lawyerId);

        if(oldRaiting == null){
            return 0;
        }

        List<Case> cases = caseRepository.findCaseByLawyerId(lawyerId);

        double sum = 0.0;
        int count = 0;

        for(Case i : cases){

            Review review = reviewRepository.findReviewByCaseId(i.getId());

            if(review != null){
                sum += review.getRating();
                count++;
            }
        }

        if(count == 0){
            return 1;
        }

        double raite = sum / count;

        oldRaiting.setRating(raite);

        raitingRepository.save(oldRaiting);

        return 2;
    }

}