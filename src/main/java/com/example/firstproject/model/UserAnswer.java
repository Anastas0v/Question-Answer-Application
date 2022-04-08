package com.example.firstproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_answer")
public class UserAnswer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    private boolean isLiked = false;
    private boolean isDisliked = false;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Answer getAnswer()
    {
        return answer;
    }

    public void setAnswer(Answer answer)
    {
        this.answer = answer;
    }

    public boolean isLiked()
    {
        return isLiked;
    }

    public void setLiked(boolean liked)
    {
        isLiked = liked;
    }

    public boolean isDisliked()
    {
        return isDisliked;
    }

    public void setDisliked(boolean disliked)
    {
        isDisliked = disliked;
    }
}
