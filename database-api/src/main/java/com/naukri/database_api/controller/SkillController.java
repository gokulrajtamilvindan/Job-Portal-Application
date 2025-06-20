package com.naukri.database_api.controller;

import com.naukri.database_api.model.Skill;
import com.naukri.database_api.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/skill")
public class SkillController {

    SkillRepository skillRepository;

    @Autowired
    public SkillController(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<Skill> create(@RequestBody Skill skills){
        skillRepository.save(skills);
        return new ResponseEntity<>(skills, HttpStatus.CREATED);
    }


    @GetMapping("/get/{skillName}")
    public ResponseEntity getSkillByName(@PathVariable String skillName){
        Skill skill = skillRepository.findByName(skillName);
        return new ResponseEntity(skill, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> findById(@PathVariable UUID id){
        Skill skills = skillRepository.findById(id).orElse(null);
        return new ResponseEntity<>(skills,HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Skill>> findAll(){
        List<Skill> skills = skillRepository.findAll();
        return new ResponseEntity<>(skills,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Skill skills){
        skillRepository.save(skills);
        return new ResponseEntity(skills,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@PathVariable UUID id){
        skillRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
