package com.example.firstproject.services;

import com.example.firstproject.dto.UserAnswerDTO;
import com.example.firstproject.model.Answer;

import java.util.List;

public interface AnswerService
{
    String validateAnswer(String answer);
    void saveAnswer(Answer answer);
    Answer createAnswer(String answer, String email, Long questionId);

    void updateUpVotes(UserAnswerDTO identifierDTO);

    void updateDownVotes(UserAnswerDTO identifierDTO);
    List<Answer> listAllAnswersByQuestion(Long questionId);
}
