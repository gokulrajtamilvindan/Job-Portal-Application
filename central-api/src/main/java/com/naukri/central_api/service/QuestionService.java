package com.naukri.central_api.service;

import com.naukri.central_api.connector.DataBaseApiConnector;
import com.naukri.central_api.model.Questions;
import com.naukri.central_api.utility.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    DataBaseApiConnector dbApiConnector;
    MappingUtility mappingUtility;

    @Autowired
    public QuestionService (DataBaseApiConnector dbApiConnector,
                            MappingUtility mappingUtility) {
        this.dbApiConnector = dbApiConnector;
        this.mappingUtility = mappingUtility;
    }

    public List<Questions> getAllQuestions(List<String> questionsList) {
        List<Questions> questions = new ArrayList<>();
        for (String question : questionsList) {
            Questions q = mappingUtility.createQuestionFromQuestionName(question, true);
            q = this.saveQuestion(q);
            questions.add(q);
        }
        return questions;
    }

    /**
     * Work of this function is to save the question inside the database.
     * @param questions
     * @return
     */

    public Questions saveQuestion(Questions questions) {
        return dbApiConnector.callCreateQuestionEndpoint(questions);
    }

}
