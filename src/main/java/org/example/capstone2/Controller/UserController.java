package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.APi.ApiResponse;
import org.example.capstone2.Model.User;
import org.example.capstone2.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getUsers(){
        List<User> users = userService.getUsers();
        if(users.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no users in the system"));
        }

        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }
        boolean success = userService.updateUser(id, user);

        if(success){
            return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        boolean success = userService.deleteUser(id);

        if(success){
            return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable String email,@PathVariable String password){
        int success= userService.login(email,password);

        if(success==0){
            return ResponseEntity.status(400).body(new ApiResponse("wrong email"));
        } else if (success==1) {
            return ResponseEntity.status(200).body(new ApiResponse("login successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong password"));
    }
}
