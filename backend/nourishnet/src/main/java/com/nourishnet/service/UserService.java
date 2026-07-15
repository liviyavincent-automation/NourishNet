package com.nourishnet.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nourishnet.model.User;
import com.nourishnet.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
    return userRepository.findAll();
    //to return the list of all users
}
public User getUserById(int id) {

    return userRepository.findById(id).orElse(null);

}
public User updateUser(int id, User updatedUser) {

    User existingUser = userRepository.findById(id).orElse(null);

    if (existingUser != null) {

        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }

    return null;
}
public String deleteUser(int id) {

    if(userRepository.existsById(id)) {

        userRepository.deleteById(id);
        return "User deleted successfully";

    }

    return "User not found";
}
public User loginUser(String email, String password) {

    return userRepository.findByEmailAndPassword(email, password);

}
public List<User> getVolunteers() {

    return userRepository.findByRole("Volunteer");

}
}