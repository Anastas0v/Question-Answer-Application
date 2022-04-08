package com.example.firstproject.repository;

import com.example.firstproject.model.UserAnswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long>
{
    @Query("SELECT u FROM UserAnswer u WHERE u.user.id = ?1 AND u.answer.id = ?2")
    public UserAnswer findByUserAndAnswer(Long userId, Long answerId);
}
