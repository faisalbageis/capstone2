package org.example.capstone2.Repository;

import org.example.capstone2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
User findUserById(Integer id);
User findUserByEmail(String email);
}
