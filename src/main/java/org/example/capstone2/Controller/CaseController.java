package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.APi.ApiResponse;
import org.example.capstone2.Model.Case;
import org.example.capstone2.Service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Case")
@RequiredArgsConstructor
public class CaseController {

    private final CaseService caseService;

    @GetMapping("/get")
    public ResponseEntity<?> getCases(){
        List<Case> cases = caseService.getCases();

        if(cases.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no Cases in the system"));
        }

        return ResponseEntity.status(200).body(cases);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCase(@RequestBody @Valid Case newCase, Errors errors){
        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        int success = caseService.addCase(newCase);

        if(success == 0){
            return ResponseEntity.status(400).body(new ApiResponse("request id not found"));
        }else if(success == 1){
            return ResponseEntity.status(400).body(new ApiResponse("user id not found"));
        }else if(success == 2){
            return ResponseEntity.status(400).body(new ApiResponse("lawyer id not found"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("case added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCase(@PathVariable Integer id, @RequestBody @Valid Case newCase, Errors errors){
        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        int success = caseService.updateCase(id, newCase);

        if(success == 0){
            return ResponseEntity.status(400).body(new ApiResponse("request id not found"));
        }else if(success == 1){
            return ResponseEntity.status(400).body(new ApiResponse("user id not found"));
        }else if(success == 2){
            return ResponseEntity.status(400).body(new ApiResponse("lawyer id not found"));
        }else if(success == 3){
            return ResponseEntity.status(400).body(new ApiResponse("case id not found"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("case updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCase(@PathVariable Integer id){
        boolean success = caseService.deleteCase(id);

        if(success){
            return ResponseEntity.status(200).body(new ApiResponse("case deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }
    @GetMapping("/search/id/{id}")
    public ResponseEntity<?> getCaseById(@PathVariable Integer id){
        Case foundCase = caseService.getCaseByID(id);
        if(foundCase==null){
            return ResponseEntity.status(400).body(new ApiResponse("no case with this id"));
        }
        return ResponseEntity.status(200).body(foundCase);
    }

    @GetMapping("/search/lawyer/{lawyerId}")
    public ResponseEntity<?> getLawyerCases(@PathVariable Integer lawyerId){
        List<Case> found = caseService.getCasesByLawyerId(lawyerId);
        if(found.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("you dont have cases"));
        }
        return ResponseEntity.status(200).body(found);
    }

    @GetMapping("/search/user/{UserId}")
    public ResponseEntity<?> getUserCases(@PathVariable Integer UserId){
        List<Case> found = caseService.getCasesByUserId(UserId);
        if(found.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("you dont have cases"));
        }
        return ResponseEntity.status(200).body(found);
    }

    @PutMapping("/close/{CaseId}/{lawyerId}")
    public ResponseEntity<?> closeCase(@PathVariable Integer CaseId , @PathVariable Integer lawyerId){
        int success = caseService.closeCase(CaseId, lawyerId);

        if(success==0){
            return ResponseEntity.status(400).body(new ApiResponse("case not found"));
        }else if(success==1){
            return ResponseEntity.status(400).body(new ApiResponse("case is closed"));
        }else if(success==2){
            return ResponseEntity.status(200).body(new ApiResponse("case closed successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("lawyer id dont match"));
    }
}
