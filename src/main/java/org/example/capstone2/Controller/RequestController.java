package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.APi.ApiResponse;
import org.example.capstone2.Model.Request;
import org.example.capstone2.Service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/Request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/get")
    public ResponseEntity<?> getRequests(){
        List<Request> requests = requestService.getRequests();
        if(requests.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no Requests in the system"));
        }

        return ResponseEntity.status(200).body(requests);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRequest(@RequestBody @Valid Request request , Errors errors){
        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

       int success= requestService.addRequest(request);
        if(success ==0){
            return ResponseEntity.status(400).body(new ApiResponse("user id not found"));
        }else if(success ==1){
            return ResponseEntity.status(400).body(new ApiResponse("lawyer id not found"));
        } else if (success==2) {
            return ResponseEntity.status(200).body(new ApiResponse("request added successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("lawyer is not active"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRequest(@PathVariable Integer id, @RequestBody @Valid Request request , Errors errors){
        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }
        int success = requestService.updateRequest(id, request);

        if(success==0){
            return ResponseEntity.status(400).body(new ApiResponse("user id not found"));
        } else if (success ==1) {
            return ResponseEntity.status(400).body(new ApiResponse("lawyer id not found"));
        } else if (success==2) {
            return ResponseEntity.status(400).body(new ApiResponse("request id not found"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("Request updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLawyer(@PathVariable Integer id){
        boolean success = requestService.deleteRequest(id);

        if(success){
            return ResponseEntity.status(200).body(new ApiResponse("request deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }
    @GetMapping("/search/lawyer/{lawyerId}")
    public ResponseEntity<?> getRequestsByLawyerId(@PathVariable Integer lawyerId){
        List<Request> requests = requestService.getLawyerRequests(lawyerId);

        if(requests.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("you dont have requests"));
        }

        return ResponseEntity.status(200).body(requests);
    }


    @GetMapping("/search/user/{UserId}")
    public ResponseEntity<?> getRequestsByUserID(@PathVariable Integer UserId){
        List<Request> requests = requestService.getUserRequests(UserId);

        if(requests.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("you dont have requests"));
        }

        return ResponseEntity.status(200).body(requests);
    }

    @PutMapping("/accept/{RequestId}/{lawyerId}")
    public ResponseEntity<?> acceptRequest(@PathVariable Integer RequestId , @PathVariable Integer lawyerId){
        int success = requestService.acceptRequest(RequestId,lawyerId);

        if(success ==0){
            return ResponseEntity.status(400).body(new ApiResponse("request id not found"));
        }else if(success ==1){
            return ResponseEntity.status(400).body(new ApiResponse("request is not pending"));
        } else if (success ==2) {
            return ResponseEntity.status(200).body(new ApiResponse("request accepted successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("lawyer id dont match"));
        }
    }

    @PutMapping("/reject/{requestId}/{lawyerId}")
    public ResponseEntity<?> rejectRequest(@PathVariable Integer requestId,@PathVariable Integer lawyerId){
        int success=requestService.rejectRequest(requestId,lawyerId);

        if(success==0){
            return ResponseEntity.status(400).body(new ApiResponse("request id not found"));
        } else if (success==1) {
            return ResponseEntity.status(400).body(new ApiResponse("request is not pending"));
        }else if(success==2) {
            return ResponseEntity.status(200).body(new ApiResponse("request rejected successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("lawyer id do not match"));
        }
    }

    @PutMapping("/cancel/{requestId}/{userId}")
    public ResponseEntity<?> cancelRequest(@PathVariable Integer requestId,@PathVariable Integer userId){
        int success = requestService.cancelRequest(requestId, userId);
        if(success==0){
            return ResponseEntity.status(400).body(new ApiResponse("request id not found"));
        } else if (success==1) {
            return ResponseEntity.status(200).body(new ApiResponse("request cancelled successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("user id do not match"));
        }
    }
}
