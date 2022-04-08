package com.example.firstproject.dto;

public class UserAnswerDTO
{
    private Long questionID;
    private Long answerID;
    private Long userId;

    public Long getQuestionID()
    {
        return questionID;
    }

    public void setQuestionID(Long questionID)
    {
        this.questionID = questionID;
    }

    public Long getAnswerID()
    {
        return answerID;
    }

    public void setAnswerID(Long answerID)
    {
        this.answerID = answerID;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
}
