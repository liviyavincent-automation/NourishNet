package com.nourishnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nourishnet.model.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
User findByEmailAndPassword(String email, String password);
  List<User> findByRole(String role);
}