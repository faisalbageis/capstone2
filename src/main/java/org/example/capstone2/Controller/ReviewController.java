package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.APi.ApiResponse;
import org.example.capstone2.Model.Review;
import org.example.capstone2.Service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get")
    public ResponseEntity<?> getReviews(){
        List<Review> reviews = reviewService.getReviews();
        if(reviews.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no Reviews in the system"));
        }
        return ResponseEntity.status(200).body(reviews);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody @Valid Review review, Errors errors){
        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        int success = reviewService.addReview(review);
        if(success==0){
            return ResponseEntity.status(400).body(new ApiResponse("case id not found"));
        } else if (success==1) {
            return ResponseEntity.status(400).body(new ApiResponse("the case is not closed "));
        } else if (success==2) {
            return ResponseEntity.status(400).body(new ApiResponse("this case already have review"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("review added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Integer id, @RequestBody @Valid Review review, Errors errors){
        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        int success = reviewService.updateReview(id, review);
        if(success == 0){
            return ResponseEntity.status(400).body(new ApiResponse("case id not found"));
        }else if(success == 1){
            return ResponseEntity.status(400).body(new ApiResponse("review id not found"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("review updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
        boolean success = reviewService.deleteReview(id);
        if(success){
            return ResponseEntity.status(200).body(new ApiResponse("review deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }
    @GetMapping("/search/{CaseId}")
    public ResponseEntity<?> getCaseReview(@PathVariable Integer CaseId){
        Review review = reviewService.getCaseReview(CaseId);

        if(review==null){
            return ResponseEntity.status(400).body(new ApiResponse("this case dont have review"));
        }
        return ResponseEntity.status(200).body(review);
    }
}