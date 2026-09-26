package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.APi.ApiResponse;
import org.example.capstone2.Model.Lawyer;
import org.example.capstone2.Model.User;
import org.example.capstone2.Service.LawyerService;
import org.example.capstone2.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Lawyer")
@RequiredArgsConstructor
public class LawyerController {
    private final LawyerService lawyerService;

    @GetMapping("/get")
    public ResponseEntity<?> getLawyers(){
        List<Lawyer> lawyers = lawyerService.getLawyers();
        if(lawyers.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no Lawyers in the system"));
        }

        return ResponseEntity.status(200).body(lawyers);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLawyer(@RequestBody @Valid Lawyer lawyer , Errors errors){
        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        lawyerService.addLawyer(lawyer);
        return ResponseEntity.status(200).body(new ApiResponse("lawyer added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLawyer(@PathVariable Integer id, @RequestBody @Valid Lawyer lawyer , Errors errors){
        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }
        boolean success = lawyerService.updateLawyer(id, lawyer);

        if(success){
            return ResponseEntity.status(200).body(new ApiResponse("lawyer updated successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLawyer(@PathVariable Integer id){
        boolean success = lawyerService.deleteLawyer(id);

        if(success){
            return ResponseEntity.status(200).body(new ApiResponse("lawyer deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @GetMapping("/search/city/{city}")
    public ResponseEntity<?> getLawyersByCity(@PathVariable String city){
         List<Lawyer> lawyers = lawyerService.getLawyersByCity(city);

         if(lawyers.isEmpty()){
             return ResponseEntity.status(400).body(new ApiResponse("there is no lawyers in this city"));
         }

         return ResponseEntity.status(200).body(lawyers);
    }

    @GetMapping("/search/specialt/{specialt}")
    public ResponseEntity<?> getLawyersBySpecialty(@PathVariable String specialt){
        List<Lawyer> lawyers = lawyerService.getLawyersBySpecialty(specialt);

        if(lawyers.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no lawyers in this Specialt"));
        }

        return ResponseEntity.status(200).body(lawyers);
    }

    @GetMapping("/search/price/{min}/{max}")
    public ResponseEntity<?> getLawyersByPrice(@PathVariable double min,@PathVariable double max){
        List<Lawyer> lawyers = lawyerService.getLawyersByPrice(min,max);

        if(lawyers.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no lawyers in this range"));
        }

        return ResponseEntity.status(200).body(lawyers);
    }

    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable String email,@PathVariable String password){
        int success= lawyerService.login(email,password);

        if(success==0){
            return ResponseEntity.status(400).body(new ApiResponse("wrong email"));
        } else if (success==1) {
            return ResponseEntity.status(200).body(new ApiResponse("login successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong password"));
    }
}
