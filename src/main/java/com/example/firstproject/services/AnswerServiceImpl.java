package com.example.firstproject.services;

import com.example.firstproject.dto.UserAnswerDTO;
import com.example.firstproject.model.Answer;
import com.example.firstproject.model.Question;
import com.example.firstproject.model.User;
import com.example.firstproject.model.UserAnswer;
import com.example.firstproject.repository.AnswerRepository;
import com.example.firstproject.repository.QuestionRepository;
import com.example.firstproject.repository.UserAnswerRepository;
import com.example.firstproject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService
{
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String validateAnswer(String answer)
    {
        StringBuilder sb = new StringBuilder();
        if (answer == null || answer.length() == 0)
        {
            sb.append("Answer Field Blank!");
        }
        return sb.toString();
    }

    @Override
    @Transactional
    public void updateUpVotes(UserAnswerDTO identifierDTO)
    {
        UserAnswer userAnswer = userAnswerRepository.findByUserAndAnswer(identifierDTO.getUserId(), identifierDTO.getAnswerID());

        if (userAnswer == null)
        {
            UserAnswer toSave = new UserAnswer();
            Answer answer = answerRepository.getById(identifierDTO.getAnswerID());
            toSave.setAnswer(answer);
            User user = userRepository.getById(identifierDTO.getUserId());
            toSave.setUser(user);
            toSave.setLiked(true);
            userAnswerRepository.save(toSave);
            int old = answer.getUpVotes();
            answer.setUpVotes(old + 1);
            answerRepository.save(answer);
        }
        else
        {
            if (userAnswer.isDisliked())
            {
                userAnswer.setDisliked(false);
                userAnswer.setLiked(true);
                userAnswerRepository.save(userAnswer);
                int old = userAnswer.getAnswer().getUpVotes();
                userAnswer.getAnswer().setUpVotes(old + 1);
                int oldDown = userAnswer.getAnswer().getDownVotes();
                userAnswer.getAnswer().setDownVotes(oldDown - 1);
                answerRepository.save(userAnswer.getAnswer());
            }
            else if (userAnswer.isLiked())
            {
                userAnswer.setLiked(false);
                userAnswerRepository.save(userAnswer);
                int old = userAnswer.getAnswer().getUpVotes();
                userAnswer.getAnswer().setUpVotes(old - 1);
                answerRepository.save(userAnswer.getAnswer());
            }
            else if (!userAnswer.isLiked() && !userAnswer.isDisliked())
            {
                userAnswer.setLiked(true);
                userAnswerRepository.save(userAnswer);
                int old = userAnswer.getAnswer().getUpVotes();
                userAnswer.getAnswer().setUpVotes(old + 1);
                answerRepository.save(userAnswer.getAnswer());
            }
        }
    }

    @Override
    @Transactional
    public void updateDownVotes(UserAnswerDTO identifierDTO)
    {
        UserAnswer userAnswer = userAnswerRepository.findByUserAndAnswer(identifierDTO.getUserId(), identifierDTO.getAnswerID());

        if (userAnswer == null)
        {
            UserAnswer toSave = new UserAnswer();
            Answer answer = answerRepository.getById(identifierDTO.getAnswerID());
            toSave.setAnswer(answer);
            User user = userRepository.getById(identifierDTO.getUserId());
            toSave.setUser(user);
            toSave.setDisliked(true);
            userAnswerRepository.save(toSave);
            int old = answer.getDownVotes();
            answer.setDownVotes(old + 1);
            answerRepository.save(answer);
        }
        else
        {
            if (userAnswer.isDisliked())
            {
                userAnswer.setDisliked(false);
                userAnswerRepository.save(userAnswer);
                int oldDown = userAnswer.getAnswer().getDownVotes();
                userAnswer.getAnswer().setDownVotes(oldDown - 1);
                answerRepository.save(userAnswer.getAnswer());
            }
            else if (userAnswer.isLiked())
            {
                userAnswer.setLiked(false);
                userAnswer.setDisliked(true);
                userAnswerRepository.save(userAnswer);
                int old = userAnswer.getAnswer().getUpVotes();
                userAnswer.getAnswer().setUpVotes(old - 1);
                int oldDown = userAnswer.getAnswer().getDownVotes();
                userAnswer.getAnswer().setDownVotes(oldDown + 1);
                answerRepository.save(userAnswer.getAnswer());
            }
            else if (!userAnswer.isLiked() && !userAnswer.isDisliked())
            {
                userAnswer.setDisliked(true);
                userAnswerRepository.save(userAnswer);
                int old = userAnswer.getAnswer().getDownVotes();
                userAnswer.getAnswer().setDownVotes(old + 1);
                answerRepository.save(userAnswer.getAnswer());
            }
        }
    }

    @Override
    public void saveAnswer(Answer answer)
    {
        answerRepository.save(answer);
    }

    @Override
    public List<Answer> listAllAnswersByQuestion(Long questionId)
    {
        return answerRepository.listAnswersByQuestion(questionId);
    }

    @Override
    public Answer createAnswer(String answer, String email, Long questionId)
    {
        Answer newAnswer = new Answer();
        newAnswer.setAnswer(answer);
        newAnswer.setCreator(email);
        newAnswer.setCreatedDate(LocalDateTime.now());
        newAnswer.setDownVotes(0);
        newAnswer.setUpVotes(0);
        try
        {
            Optional<Question> questionOptional = questionRepository.findById(questionId);
            if (questionOptional.isPresent())
            {
                newAnswer.setQuestion(questionOptional.get());
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return newAnswer;
    }
}