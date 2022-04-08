package com.example.firstproject.services;

import com.example.firstproject.dto.DeleteQuestionDTO;
import com.example.firstproject.model.Question;

public interface QuestionService
{
    void saveQuestion (Question question);
    boolean checkTitle (String title);
    boolean checkQuestion (String question);

    String validateQuestion(String question, String title);

    Question createQuestion(String question, String title, String email);

    String deleteQuestionById(DeleteQuestionDTO deleteQuestionDTO);
}
