package com.example.firstproject.dto;

public class QuestionDTO
{
    private String title;
    private String question;
    private String email;

    public QuestionDTO(String title, String question, String email)
    {
        this.title = title;
        this.question = question;
        this.email = email;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
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
