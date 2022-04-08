package com.example.firstproject.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "answer")
public class Answer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String answer;

    private String creator;

    private LocalDateTime createdDate;

    private Integer upVotes;

    private Integer downVotes;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @OneToMany(mappedBy = "answer")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserAnswer> likeAndDislikeSystemList;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public LocalDateTime getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate)
    {
        this.createdDate = createdDate;
    }

    public Integer getUpVotes()
    {
        return upVotes;
    }

    public void setUpVotes(Integer upVotes)
    {
        this.upVotes = upVotes;
    }

    public Integer getDownVotes()
    {
        return downVotes;
    }

    public void setDownVotes(Integer downVotes)
    {
        this.downVotes = downVotes;
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }
}
