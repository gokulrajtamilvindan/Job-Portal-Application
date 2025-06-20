package com.naukri.database_api.controller;

import com.naukri.database_api.model.Questions;
import com.naukri.database_api.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/questions")
public class QuestionsController {

    QuestionsRepository questionsRepository;

    @Autowired
    public QuestionsController(QuestionsRepository questionsRepository){
        this.questionsRepository = questionsRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<Questions> create(@RequestBody Questions questions){
        questionsRepository.save(questions);
        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questions> findById(@PathVariable UUID id){
        Questions questions = questionsRepository.findById(id).orElse(null);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Questions>> findAll(){
        List<Questions> questions = questionsRepository.findAll();
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Questions questions){
        questionsRepository.save(questions);
        return new ResponseEntity(questions,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@PathVariable UUID id){
        questionsRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
