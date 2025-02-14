package com.luiz.userprofileapp.controller;

import com.luiz.userprofileapp.model.UserProfile;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserProfileController {
    private Map<String,UserProfile> userProfiles;

    @PostConstruct
    public void initializeProfiles(){
        userProfiles = new HashMap<>();
        userProfiles.put("Charles", new UserProfile(
                "Charles",
                25,
                "charles@email.com",
                "France"
        ));
        userProfiles.put("Erik", new UserProfile(
                "Erik",
                19,
                "erik@email.com",
                "Sweden"
        ));
        userProfiles.put("Marilene", new UserProfile(
                "Marilene",
                25,
                "marilene@email.com",
                "Brazil"
        ));
    }

    @GetMapping("/profile/{name}")
    public ResponseEntity<Object> getProfileByUserName(@PathVariable String name){
        if(userProfiles.containsKey(name)){
            return new ResponseEntity<>(userProfiles.get(name), HttpStatus.OK);
        }
        // Error response when user Not Found
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "User not Found");
        errorResponse.put("name", name);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/profiles")
    public ResponseEntity<Object> listAllUsers(@RequestParam(required = false) Integer age){

        // List containing the filtered profiles by age
        List<UserProfile> filteredProfiles = new ArrayList<>();

        // Checks Query Parameter
        if (age != null){
            for(UserProfile profile : userProfiles.values()){
                if(profile.getAge() == age){
                    filteredProfiles.add(profile);
                }
            }
            return !filteredProfiles.isEmpty() ?
                    new ResponseEntity<>(filteredProfiles, HttpStatus.OK) :
                    new ResponseEntity<>("No Users Found", HttpStatus.NOT_FOUND);
        }
        // Default Response containing all users
        else{
            List<UserProfile> defaultResponse = new ArrayList<>(userProfiles.values());
            return new ResponseEntity<>(defaultResponse, HttpStatus.OK);
        }
    }
}
