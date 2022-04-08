package com.example.firstproject.controller;

import com.example.firstproject.dto.DeleteQuestionDTO;
import com.example.firstproject.dto.QuestionDTO;
import com.example.firstproject.model.Question;
import com.example.firstproject.services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/question")
public class QuestionController
{
    @Autowired
    private QuestionService questionService;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> saveQuestion(@RequestBody QuestionDTO questionDTO, Authentication authentication)
    {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String name = userDetails.getUsername();
        String result = questionService.validateQuestion(questionDTO.getQuestion(), questionDTO.getTitle());
        if(result.length() == 0)
        {
            Question question = questionService.createQuestion(questionDTO.getQuestion(), questionDTO.getTitle(), name);
            questionService.saveQuestion(question);
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @RequestMapping(path = "delete-question", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteQuestion(@RequestBody DeleteQuestionDTO deleteQuestionDTO)
    {
        String result = questionService.deleteQuestionById(deleteQuestionDTO);
        if (result.length() == 0)
        {
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}
