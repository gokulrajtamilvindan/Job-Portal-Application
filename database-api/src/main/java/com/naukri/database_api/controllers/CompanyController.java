package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Company;
import com.naukri.database_api.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/company")
public class CompanyController {

    CompanyRepository companyRepository;

    @Autowired
    public CompanyController (CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping("/save")
    public ResponseEntity createCompany(@RequestBody Company company) {
        companyRepository.save(company);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable UUID id) {
        Company company = companyRepository.findById(id).orElse(null);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Company>> findAll() {
        List<Company> companies = companyRepository.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Company> update(@RequestBody Company company){
        companyRepository.save(company);
        return new ResponseEntity<>(company,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        companyRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
