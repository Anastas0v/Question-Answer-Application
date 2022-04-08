package com.example.firstproject.controller;

import com.example.firstproject.dto.AnswerDTO;
import com.example.firstproject.dto.UserAnswerDTO;
import com.example.firstproject.model.Answer;
import com.example.firstproject.services.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/answer")
public class AnswerController
{
    @Autowired
    private AnswerService answerService;

    @GetMapping("/view-answer/{id}")
    public String viewAnswer(@PathVariable Long id, Model model)
    {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken))
        {
            model.addAttribute("questionId", id);
            List<Answer> answerList = answerService.listAllAnswersByQuestion(id);
            model.addAttribute("listAnswers", answerList);
            return "viewAnswer";
        }
        else
        {
            return "login";
        }
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> saveAnswer(@RequestBody AnswerDTO answerDTO)
    {
        String result = answerService.validateAnswer(answerDTO.getAnswer());
        if (result.length() == 0)
        {
            Answer answer = answerService.createAnswer(answerDTO.getAnswer(), answerDTO.getEmail(), answerDTO.getQuestionId());
            if (answer != null)
            {
                answerService.saveAnswer(answer);
            }
            else
            {
                result = "Invalid Question ID";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @RequestMapping(path = "/up-vote/{answerId}/{questionId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> incrementUpVote(@RequestBody UserAnswerDTO identifierDTO)
    {
        answerService.updateUpVotes(identifierDTO);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @RequestMapping(path = "/down-vote/{answerId}/{questionId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> incrementDownVote(@RequestBody UserAnswerDTO identifierDTO)
    {
        answerService.updateDownVotes(identifierDTO);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}