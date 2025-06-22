package com.naukri.database_api.controller;

import com.naukri.database_api.model.AppUser;
import com.naukri.database_api.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/user")
public class AppUserController {

    AppUserRepository appUserRepository;

    @Autowired
    public AppUserController(AppUserRepository userRepository) {
        this.appUserRepository = userRepository;
    }

    @PostMapping("/save")
    public ResponseEntity createUser(@RequestBody AppUser user) {
        /*
         To create the user object, we will have all the property set but only id property will not be set
         Hibernate, will see as their user object is not having id property set
         So, I should create new record inside user table.
        */
        appUserRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/email/{emailId}")
    public ResponseEntity getUserByEmail(@PathVariable String emailId) {
        AppUser user = appUserRepository.findByEmail(emailId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable UUID id) {
        AppUser user = appUserRepository.findById(id).orElse(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody AppUser user) {
        /*
         This user object will have some of the fields updated
         And to update user it should definitely have id in it
        */

        /*
         Update -> We use update functionality only when the record is already present in the database.
         You need to do what? You need to update some of its field
         So, Hibernate is saying if in your user object already id property is set already
         So, I will do what I am going to search the record which is having the same ID which you are passing in a user object
         If I will be able to find the record I will update all the updated fields which you are passing in the user object
         If I will not be able to find the record, then I will create a new record in the table.
        */

        appUserRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable UUID id) {
        appUserRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
