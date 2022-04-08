package com.example.firstproject.repository;

import com.example.firstproject.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long>
{
    @Override
    public Optional<Question> findById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Question q WHERE q.id=:id")
    public void deleteQuestionById(Long id);
}
