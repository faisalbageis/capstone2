package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        user.setStatus("active");
        userRepository.save(user);
    }

    public boolean updateUser(Integer id,User user){
        User olduser = userRepository.findUserById(id);

        if(olduser == null){
            return false;
        }

        olduser.setEmail(user.getEmail());
        olduser.setFullName(user.getFullName());
        olduser.setPhoneNumber(user.getPhoneNumber());
        olduser.setPassword(user.getPassword());
        userRepository.save(olduser);
        return true;
    }

    public boolean deleteUser(Integer id){
        User olduser = userRepository.findUserById(id);

        if(olduser == null){
            return false;
        }

        userRepository.delete(olduser);
        return true;
    }

    public int login(String email,String Password){
        User user = userRepository.findUserByEmail(email);

        if(user==null){
            return 0;
        }

        if(user.getPassword().equals(Password)){
            return 1;
        }
        return 2;
    }

}
