package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.APi.ApiResponse;
import org.example.capstone2.Model.AiRequest;
import org.example.capstone2.Service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Ai")
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService;

    @PostMapping("/find")
    public ResponseEntity<?> findSpeciality(@RequestBody @Valid AiRequest aiRequest){

        String speciality = aiService.findSpeciality(aiRequest.getDescription());

        if(speciality==null||speciality.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("Unable to determine specialty"));
        }

        return ResponseEntity.status(200).body(speciality);
    }
}
