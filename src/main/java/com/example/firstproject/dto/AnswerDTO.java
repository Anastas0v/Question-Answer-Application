package com.example.firstproject.dto;

public class AnswerDTO
{
    private String answer;

    private String email;

    private Long questionId;

    public Long getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(Long questionId)
    {
        this.questionId = questionId;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
