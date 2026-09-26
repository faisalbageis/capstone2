package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.Model.Case;
import org.example.capstone2.Model.Request;
import org.example.capstone2.Model.Lawyer;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.CaseRepository;
import org.example.capstone2.Repository.RequestRepository;
import org.example.capstone2.Repository.LawyerRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;
    private final RequestRepository requestRepository;
    private final LawyerRepository lawyerRepository;
    private final UserRepository userRepository;

    public List<Case> getCases() {
        return caseRepository.findAll();
    }

    public int addCase(Case caseObject) {

        Request request = requestRepository.findRequestById(caseObject.getRequestId());

        if (request == null) {
            return 0;
        }

        User user = userRepository.findUserById(caseObject.getUserId());

        if (user == null) {
            return 1;
        }

        Lawyer lawyer = lawyerRepository.findLawyerById(caseObject.getLawyerId());

        if (lawyer == null) {
            return 2;
        }

        caseRepository.save(caseObject);
        return 3;
    }

    public int updateCase(Integer id, Case caseObject) {

        Request request = requestRepository.findRequestById(caseObject.getRequestId());

        if (request == null) {
            return 0;
        }

        User user = userRepository.findUserById(caseObject.getUserId());

        if (user == null) {
            return 1;
        }

        Lawyer lawyer = lawyerRepository.findLawyerById(caseObject.getLawyerId());

        if (lawyer == null) {
            return 2;
        }

        Case oldCase = caseRepository.findCaseById(id);

        if (oldCase == null) {
            return 3;
        }

        oldCase.setRequestId(caseObject.getRequestId());
        oldCase.setUserId(caseObject.getUserId());
        oldCase.setLawyerId(caseObject.getLawyerId());
        oldCase.setCaseType(caseObject.getCaseType());
        oldCase.setDescription(caseObject.getDescription());

        caseRepository.save(oldCase);
        return 4;
    }

    public boolean deleteCase(Integer id) {

        Case oldCase = caseRepository.findCaseById(id);

        if (oldCase == null) {
            return false;
        }

        caseRepository.delete(oldCase);
        return true;
    }


    public Case getCaseByID(Integer id){
        return caseRepository.findCaseById(id);
    }


    public List<Case> getCasesByLawyerId(Integer lawyerId){
        return caseRepository.findCaseByLawyerId(lawyerId);
    }

    public List<Case> getCasesByUserId(Integer userId){
        return caseRepository.findCaseByUserId(userId);
    }

    public int closeCase(Integer CaseId,Integer lawyerId){
        Case foundCase = caseRepository.findCaseById(CaseId);
        if(foundCase== null){
            return 0;
        }

        if(foundCase.getStatus().equalsIgnoreCase("closed")){
            return 1;
        }

        if(foundCase.getLawyerId().equals(lawyerId)){
            foundCase.setStatus("closed");
            caseRepository.save(foundCase);

            return 2;
        }
        return 3;
    }
}