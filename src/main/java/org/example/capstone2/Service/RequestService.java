package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.Model.Case;
import org.example.capstone2.Model.Lawyer;
import org.example.capstone2.Model.Request;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.LawyerRepository;
import org.example.capstone2.Repository.RequestRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final LawyerRepository lawyerRepository;
    private final UserRepository userRepository;
    private final CaseService caseService;
    private final EmailService emailService;

    public List<Request> getRequests(){
        return requestRepository.findAll();
    }

    public int addRequest(Request request){
        User user = userRepository.findUserById(request.getUserId());
        if(user==null){
            return 0;
        }

        Lawyer lawyer = lawyerRepository.findLawyerById(request.getLawyerId());
        if(lawyer==null){
            return 1;
        }

        requestRepository.save(request);
        return 2;
    }

    public int updateRequest(Integer id,Request request){

        User user = userRepository.findUserById(request.getUserId());
        if(user==null){
            return 0;
        }

        Lawyer lawyer = lawyerRepository.findLawyerById(request.getLawyerId());
        if(lawyer==null){
            return 1;
        }
        Request oldRequest = requestRepository.findRequestById(id);

        if(oldRequest == null){
            return 2;
        }

        oldRequest.setCaseType(request.getCaseType());
        oldRequest.setDescription(request.getDescription());
        oldRequest.setLawyerId(request.getLawyerId());
        oldRequest.setUserId(request.getUserId());
        requestRepository.save(oldRequest);
        return 3;
    }

    public boolean deleteRequest(Integer id){
        Request oldRequest = requestRepository.findRequestById(id);

        if(oldRequest == null){
            return false;
        }

        requestRepository.delete(oldRequest);
        return true;
    }

    public List<Request> getLawyerRequests(Integer lawyerId){
       return  requestRepository.findRequestByLawyerId(lawyerId);

    }

    public List<Request> getUserRequests(Integer UserId){
        return requestRepository.findRequestByUserId(UserId);
    }

    public int acceptRequest(Integer RequestID,Integer lawyerID){
        Request request = requestRepository.findRequestById(RequestID);

        if(request ==null){
            return 0;
        }
        if(!request.getStatus().equalsIgnoreCase("pending")) {
            return 1;
        }
            if (request.getLawyerId().equals(lawyerID)) {
                request.setStatus("Accepted");
                requestRepository.save(request);
                Case newcase = new Case();
                newcase.setRequestId(request.getId());
                newcase.setLawyerId(request.getLawyerId());
                newcase.setUserId(request.getUserId());
                newcase.setCaseType(request.getCaseType());
                newcase.setDescription(request.getDescription());
                caseService.addCase(newcase);

                User user = userRepository.findUserById(request.getUserId());
                emailService.sendEmail(user.getEmail(),
                        "Request Accepted",
                        "Your request has been accepted by the lawyer.");

                return 2;
            }
        return 3;
    }


    public int rejectRequest(Integer requestId,Integer LawyerId){

        Request request = requestRepository.findRequestById(requestId);

        if(request==null){
            return 0;
        }

        if(!request.getStatus().equalsIgnoreCase("pending")) {
            return 1;
        }

        if(request.getLawyerId().equals(LawyerId)){
            request.setStatus("Rejected");
            requestRepository.save(request);


            User user = userRepository.findUserById(request.getUserId());
            emailService.sendEmail(user.getEmail(),
                    "Request Rejected",
                    "Your request has been rejected by the lawyer.");


            return 2;
        }
        return 3;
    }


    public int cancelRequest(Integer requestId,Integer userID){
        Request request = requestRepository.findRequestById(requestId);

        if(request==null){
            return 0;
        }

        if(request.getUserId().equals(userID)){
            request.setStatus("Cancelled");
            requestRepository.save(request);
            User user = userRepository.findUserById(userID);
            emailService.sendEmail(user.getEmail(),
                    "Request Cancelled",
                    "Your consultation request has been cancelled successfully.");
            return 1;
        }
        return 2;
    }
}
