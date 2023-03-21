package com.hridoykrisna.stdapi.service.IMPL;

import com.hridoykrisna.stdapi.model.User;
import com.hridoykrisna.stdapi.repository.UserRepo;
import com.hridoykrisna.stdapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {
    private final UserRepo userRepo;
    public UserServiceIMPL(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public User update(Long Id, User user) {
        Optional<User> user1 = userRepo.findById(Id);
        if (user1.isPresent()){
            user.setId(Id);
           return userRepo.save(user);

        }
        return null;
    }

    @Override
    public String delete(Long Id) {
        Optional<User> user1 = userRepo.findById(Id);
        if (user1.isPresent()){
            userRepo.deleteById(Id);
            return "Deleted Successfully";

        }
        return "Not Found";
    }

    @Override
    public User getDetails(Long Id) {
        Optional<User> user1 = userRepo.findById(Id);
        if (user1.isPresent()){

            return user1.get();

        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        User user = userRepo.findByUsername(username);

        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }
}
