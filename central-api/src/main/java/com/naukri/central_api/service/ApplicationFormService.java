package com.naukri.central_api.service;

import com.naukri.central_api.model.ApplicationForm;
import com.naukri.central_api.model.Questions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationFormService {

    QuestionService questionService;

    public ApplicationFormService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public ApplicationForm createApplicationFormByQuestions(List<String> questionsList) {

        List<Questions> questions = questionService.getAllQuestions(questionsList);
        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.setQuestionsList(questions);
        return applicationForm;

    }

}
