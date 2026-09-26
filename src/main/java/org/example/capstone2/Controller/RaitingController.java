package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.APi.ApiResponse;
import org.example.capstone2.Model.Raiting;
import org.example.capstone2.Service.RaitingService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Raiting")
@RequiredArgsConstructor
public class RaitingController {

    private final RaitingService raitingService;

    @GetMapping("/get")
    public ResponseEntity<?> getRaitings(){

        List<Raiting> raitings = raitingService.getRaitings();

        if(raitings.isEmpty()){
            return ResponseEntity.status(400)
                    .body(new ApiResponse("there is no Raitings in the system"));
        }

        return ResponseEntity.status(200).body(raitings);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRaiting(@RequestBody @Valid Raiting raiting, Errors errors){

        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        int success = raitingService.addRaiting(raiting);

        if(success==0){
            return ResponseEntity.status(400).body(new ApiResponse("lawyer id not found"));
        } else if (success==1) {
            return ResponseEntity.status(400).body(new ApiResponse("lawyer dont have cases"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("raiting added successfully"));
    }

    @PutMapping("/update/{lawyerId}")
    public ResponseEntity<?> updateRaiting(@PathVariable Integer lawyerId){
        int success = raitingService.updateRaiting(lawyerId);

        if(success==0){
            return ResponseEntity.status(400).body(new ApiResponse("there is no rating for this lawyer"));
        } else if (success==1) {
            return ResponseEntity.status(400).body(new ApiResponse("lawyer dont have cases"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("rating updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRaiting(@PathVariable Integer id){

        boolean success = raitingService.deleteRaiting(id);

        if(success){
            return ResponseEntity.status(200).body(new ApiResponse("raiting deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @GetMapping("/search/{lawyerId}")
    public ResponseEntity<?> getlawyerRaiting(@PathVariable Integer lawyerId){
        Raiting raiting = raitingService.getLawyerRaiting(lawyerId);
        if(raiting==null){
            return ResponseEntity.status(400).body(new ApiResponse("lawyer dont have rating"));
        }
        return ResponseEntity.status(200).body(raiting);
    }

}