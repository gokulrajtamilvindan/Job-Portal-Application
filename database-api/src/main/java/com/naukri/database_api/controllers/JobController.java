package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Job;
import com.naukri.database_api.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/job")
public class JobController {

    JobRepository jobRepository;

    @Autowired
    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @PostMapping("/save")
    public ResponseEntity create(@RequestBody Job job) {
        jobRepository.save(job);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable UUID id){
        Job job = jobRepository.findById(id).orElse(null);
        return new ResponseEntity<>(job,HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Job>> findAll(){
        List<Job> jobs = jobRepository.findAll();
        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Job job){
        jobRepository.save(job);
        return new ResponseEntity(job,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@PathVariable UUID id){
        jobRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
