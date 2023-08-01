package com.HealthGalactica.UserMenagment.Service;

import com.HealthGalactica.UserMenagment.Model.UserModel;
import com.HealthGalactica.UserMenagment.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserModel> getAll() {
        List<UserModel> allUser = userRepository.findAll();
        return allUser;
    }

    public void addUser(UserModel user) {
        userRepository.save(user);
    }

    public boolean checkData(UserModel userModel) {
        if(userModel.getPassword().matches(".*[0-9].*")){
            return false;
        }
        return true;
    }

    public UserModel fixData(UserModel userModel) {
        char firstnamechar=userModel.getName().charAt(0);
        if(Character.isUpperCase(firstnamechar)){
            String name=userModel.getName().toString();
            name=name.substring(1);
            String firstletter= String.valueOf(firstnamechar);
            name=firstletter.toUpperCase()+name;
            userModel.setName(name);
        }
        firstnamechar=userModel.getSurname().charAt(0);
        if(Character.isUpperCase(firstnamechar)){
            String sname=userModel.getSurname().toString();
            sname=sname.substring(1);
            String firstletter= String.valueOf(firstnamechar);
            sname=firstletter.toUpperCase()+sname;
            userModel.setSurname(sname);
        }
        return userModel;
    }

    public void updateUser(UserModel user) {
        UserModel newUser = new UserModel();
        newUser.setId(user.getId());
        newUser.setUserType(user.getUserType());
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        userRepository.save(newUser);
    }

    public Optional<UserModel> findByMailAndPass(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }
}
