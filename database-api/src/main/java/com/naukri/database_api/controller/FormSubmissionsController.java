package com.naukri.database_api.controller;

import com.naukri.database_api.model.FormSubmissions;
import com.naukri.database_api.repository.FormSubmissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/submission")
public class FormSubmissionsController {

    FormSubmissionsRepository formSubmissionsRepository;

    @Autowired
    public FormSubmissionsController(FormSubmissionsRepository formSubmissionsRepository){
        this.formSubmissionsRepository = formSubmissionsRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<FormSubmissions> create(@RequestBody FormSubmissions formSubmissions){
        formSubmissionsRepository.save(formSubmissions);
        return new ResponseEntity<>(formSubmissions, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormSubmissions> findById(@PathVariable UUID id){
        FormSubmissions formSubmission = formSubmissionsRepository.findById(id).orElse(null);
        return new ResponseEntity<>(formSubmission,HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<FormSubmissions>> findAll(){
        List<FormSubmissions> formSubmissions = formSubmissionsRepository.findAll();
        return new ResponseEntity<>(formSubmissions,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<FormSubmissions> update(@RequestBody FormSubmissions formSubmission){
        formSubmissionsRepository.save(formSubmission);
        return new ResponseEntity<>(formSubmission,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        formSubmissionsRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
