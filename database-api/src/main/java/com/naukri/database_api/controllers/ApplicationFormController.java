package com.naukri.database_api.controllers;

import com.naukri.database_api.models.ApplicationForm;
import com.naukri.database_api.repositories.ApplicationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/form")
public class ApplicationFormController {

    ApplicationFormRepository applicationFormRepository;

    @Autowired
    public ApplicationFormController(ApplicationFormRepository applicationFormRepository) {
        this.applicationFormRepository = applicationFormRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<ApplicationForm> create(@RequestBody ApplicationForm applicationForm) {
        applicationFormRepository.save(applicationForm);
        return new ResponseEntity<>(applicationForm, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable UUID id){
        ApplicationForm applicationForm = applicationFormRepository.findById(id).orElse(null);
        return new ResponseEntity<>(applicationForm,HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ApplicationForm>> findAll(){
        List<ApplicationForm> applicationForms = applicationFormRepository.findAll();
        return new ResponseEntity<>(applicationForms,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApplicationForm> update(@RequestBody ApplicationForm applicationForm){
        applicationFormRepository.save(applicationForm);
        return new ResponseEntity<>(applicationForm,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        applicationFormRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
