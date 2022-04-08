package com.example.firstproject.repository;

import com.example.firstproject.model.Answer;
import com.example.firstproject.model.UserAnswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long>
{
    @Query(value = "SELECT * FROM Answer a WHERE a.question_id = :id ", nativeQuery = true)
    List<Answer> listAnswersByQuestion(@Param("id") Long questionId);

    @Transactional
    @Modifying
    @Query("UPDATE Answer a SET a.downVotes = :downVotes WHERE a.id = :answerId")
    int updateDownVotes(@Param("answerId") Long answerId, @Param("downVotes") int downVotes);

    Answer getById(UserAnswer answerId);
}
